package com.mall.choisinsa.llmgateway.domain.chat.dto

data class ChatResponse(
    val sessionId: String,
    val answer: String,
    val intent: String,
    val citations: List<String>,
    val confidence: Double
)