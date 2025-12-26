package com.mall.choisinsa.llmgateway.domain.chat.session

data class ChatSession(
    val sessionId: String,
    val summary: String = "",
    val messages: List<ChatTurn> = emptyList(),
) {
    data class ChatTurn(
        val role: Role,
        val content: String,
    )

    enum class Role {
        SYSTEM, USER, ASSISTANT
    }
}