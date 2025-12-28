package com.mall.choisinsa.oauth2.controller.request

class KakaoOauthTokenRequestDto(
    val grant_type: String,
    val client_id: String,
    val redirect_uri: String,
    val code: String,
    val client_secret: String,
)