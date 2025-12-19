package com.mall.choisinsa.member.domain.dto.request

data class MemberPrivacySearchRequest(
    val nameEncrypted: String,
    val emailEncrypted: String,
    val phoneNumberEncrypted: String,
    val birthdayEncrypted: String,
)
