package com.mall.choisinsa.llmgateway.domain.chat.session

import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.enumeration.RedisTTL
import com.mall.choisinsa.service.RedisService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RedisChatSessionStore(
    private val redisService: RedisService,
    private val objectMapper: ObjectMapper,
    @Value("\${llm.session.ttl-seconds}") private val ttlSeconds: Long
) : ChatSessionStore {
    private fun key(
        sessionId: String
    ) = "chat-session:$sessionId"

    override fun load(sessionId: String): ChatSession {
        val raw = redisService.get(key(sessionId)) ?: return ChatSession(sessionId)
        return objectMapper.readValue(raw, ChatSession::class.java)
    }

    override fun save(
        session: ChatSession
    ) {
        val raw = objectMapper.writeValueAsString(session)
        redisService.save(key(session.sessionId), raw, RedisTTL.TTL_SECONDS)
    }
}