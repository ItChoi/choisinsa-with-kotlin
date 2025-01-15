package com.mall.choisinsa.common.enumeration.terms

enum class TermsKind(
    private val desc: String,
) {
    PERSONAL_INFO_AGREEMENT("개인정보 수집 및 이용 동의"),
    STORE_USAGE_TERMS("스토어 이용 약관 동의"),
    UNDER_14_RESTRICTION("만 14세 미만 가입 제한"),
    MARKETING_INFO_AGREEMENT("마케팅 활용 및 광고성 정보 수신 동의"),
}