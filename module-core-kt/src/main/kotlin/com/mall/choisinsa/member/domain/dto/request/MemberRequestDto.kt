package com.mall.choisinsa.member.domain.dto.request

import com.mall.choisinsa.common.enumeration.LoginType

data class MemberRequestDto(
    private val loginType: LoginType = LoginType.HOME,
    val loginId: String,
    var password: String,
    val email: String,
    val recommenderLoginId: String? = null,

    val phoneNumber: String,
    //val ci: String? = null,
) {
    init {
//        require(
//            MemberValidation.isValidLoginId(this.loginId)
//                    && MemberValidation.isValidPassword(this.password)
//                    && MemberValidation.isValidEmail(this.email)
//        )
    }
}