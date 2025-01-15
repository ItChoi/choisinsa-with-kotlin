package com.mall.choisinsa.common.enumeration.terms

enum class TermsStatus(
    private val desc: String,
) {
    ACTIVE("활성화"),
    IN_ACTIVE("비활성화"),
    EXPIRED("만료"),
}