package com.mall.choisinsa.llmgateway.domain.chat.session

interface ChatSessionStore {
    fun load(sessionId: String): ChatSession
    fun save(session: ChatSession)

}