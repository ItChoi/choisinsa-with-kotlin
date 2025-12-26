package com.mall.choisinsa.common.dto

import org.jetbrains.annotations.NotNull

data class ChatRequest(
    @field:NotNull val sessionId: String,
    @field:NotNull val message: String
)