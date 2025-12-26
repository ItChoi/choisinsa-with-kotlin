package com.mall.choisinsa.llmgateway.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.dto.ChatRequest
import com.mall.choisinsa.llmgateway.domain.chat.LlmProps
import com.mall.choisinsa.llmgateway.domain.chat.dto.ChatResponse
import com.mall.choisinsa.llmgateway.domain.chat.dto.LlmAnswer
import com.mall.choisinsa.llmgateway.domain.chat.session.ChatSession
import com.mall.choisinsa.llmgateway.domain.chat.session.ChatSessionStore
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.ResponseFormat
import org.springframework.stereotype.Service


@Service
class ChatService(
    private val chatClient: ChatClient,
    private val sessionStore: ChatSessionStore,
    private val ragService: RagService,
    private val objectMapper: ObjectMapper,
    private val props: LlmProps

) {
    fun chat(req: ChatRequest): ChatResponse {
        val session = sessionStore.load(req.sessionId)

        // 1) RAG: 질문과 관련된 문서 컨텍스트 구성
        val rag = ragService.retrieve(req.message)

        // 2) 프롬프트 구성 (System + Memory(summary) + RAG + User)
        val systemPrompt = """
            You are a helpful assistant for a backend service.
            Follow these rules:
            - You MUST output JSON that matches the provided JSON schema.
            - If you don't have enough context, set confidence low and shouldHandoff=true.
            - Prefer answers grounded in the given CONTEXT.
        """.trimIndent()

        val memoryPrompt = buildString {
            if (session.summary.isNotBlank()) {
                appendLine("SESSION_SUMMARY:")
                appendLine(session.summary)
            }
            if (session.messages.isNotEmpty()) {
                appendLine("\nRECENT_TURNS:")
                session.messages.takeLast(props.maxMessages).forEach { turn ->
                    appendLine("${turn.role}: ${turn.content}")
                }
            }
        }.trim()

        val userPrompt = """
            CONTEXT:
            ${rag.contextText}

            USER_QUESTION:
            ${req.message}
        """.trimIndent()

        // 3) Structured Output(JSON Schema) 강제
        // Spring AI 문서에 responseFormat.type=JSON_SCHEMA, schema 설정이 존재 :contentReference[oaicite:15]{index=15}

        val options = OpenAiChatOptions.builder()
            .responseFormat(ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, LlmAnswer.JSON_SCHEMA))
            .build()

        val rawJson = chatClient.prompt()
            .system(systemPrompt)
            .user(if (memoryPrompt.isBlank()) userPrompt else "$memoryPrompt\n\n$userPrompt")
            .options(options)
            .call()
            .content()

        // 4) JSON 파싱 (운영 안정성 핵심)
        val llmAnswer = runCatching {
            objectMapper.readValue(rawJson, LlmAnswer::class.java)
        }.getOrElse { ex ->
            // 파싱 실패는 실무에서 반드시 대비해야 함
            // - 여기서는 안전하게 fallback 응답
            LlmAnswer(
                intent = "PARSE_ERROR",
                answer = "죄송합니다. 응답 형식을 처리하는 중 문제가 발생했어요. 다시 시도해 주세요.",
                citations = emptyList(),
                confidence = 0.0,
                shouldHandoff = true
            )
        }

        // 5) 세션 업데이트 (최근 N턴만 유지)
        val updated = updateSession(session, req.message, llmAnswer.answer)

        // 6) 요약 갱신(단순 버전): 메시지가 많아지면 요약 생성
        val summarized = maybeSummarize(updated)

        sessionStore.save(summarized)

        return ChatResponse(
            sessionId = req.sessionId,
            answer = llmAnswer.answer,
            intent = llmAnswer.intent,
            citations = (llmAnswer.citations + rag.citations).distinct(),
            confidence = llmAnswer.confidence
        )
    }

    private fun updateSession(
        session: ChatSession,
        userMsg: String,
        assistantMsg: String
    ): ChatSession {
        val newTurns = session.messages + listOf(
            ChatSession.ChatTurn(ChatSession.Role.USER, userMsg),
            ChatSession.ChatTurn(ChatSession.Role.ASSISTANT, assistantMsg)
        )
        val trimmed = newTurns.takeLast(props.maxMessages)
        return session.copy(messages = trimmed)
    }

    private fun maybeSummarize(session: ChatSession): ChatSession {
        // “학습용”으로 아주 단순한 정책:
        // 최근 대화가 maxMessages에 가까워지면 summary를 한 번 갱신
        if (session.messages.size < props.maxMessages) return session

        val summarySystem = "Summarize the conversation briefly for future context. Output plain text."
        val summaryUser = session.messages.joinToString("\n") { "${it.role}: ${it.content}" }

        val summary = chatClient.prompt()
            .system(summarySystem)
            .user(summaryUser)
            .call()
            .content() ?: ""

        return session.copy(summary = summary)
    }

}