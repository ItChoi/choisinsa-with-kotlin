package com.mall.choisinsa.oauth2.controller.request

data class Oauth2LoginRequestDto(
    // 카카오 로그인 요청 정보 START
    val code: String,
    val state: String,
    val error: String,
    val error_description: String,
// 카카오 로그인 요청 정보 END
)
