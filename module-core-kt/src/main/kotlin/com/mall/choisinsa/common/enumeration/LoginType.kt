package com.mall.choisinsa.common.enumeration

enum class LoginType (
    private val desc: String,
) {
    HOME("홈페이지 가입"),
    KAKAO("카카오"),
    APPLE("애플"),
    NAVER("네이버"),
    FACEBOOK("페이스북"),
}