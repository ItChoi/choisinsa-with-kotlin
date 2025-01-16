package com.mall.choisinsa.dto.response

data class TokenResponseDto(
    private val accessToken: String,
    private val refreshToken: String,
)
