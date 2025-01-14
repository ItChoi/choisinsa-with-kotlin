package com.mall.choisinsa.dto.request.member

import com.mall.choisinsa.common.enumeration.LoginType

data class MemberRequestDto(
    private val loginType: LoginType = LoginType.HOME,
    private val loginId: String,
    private val password: String,
    private val email: String,
    private val recommenderLoginId: String?
) {
}