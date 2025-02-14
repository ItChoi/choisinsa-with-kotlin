package com.mall.choisinsa.common.enumeration.terms

enum class TermsKind(
    private val desc: String,
) {
    PERSONAL_INFO("개인정보"),
    STORE_USAGE("스토어 이용"),
    RESTRICTION("가입 제한"),
    MARKETING("마케팅"),
    ADVERTISEMENT("광고"),
}