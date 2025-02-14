package com.mall.choisinsa.terms.domain.dto

data class MemberTermsRequest(
    val memberTermsId: Long,
    val isAgree: Boolean,
)
