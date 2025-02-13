package com.mall.choisinsa.member.domain.dto.request

import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.common.util.MemberValidation

data class MemberRequestDto(
    private val loginType: LoginType = LoginType.HOME,
    val loginId: String,
    var password: String,
    var email: String,
    val recommenderLoginId: String? = null,

    var phoneNumber: String,
    val ci: String? = null,
) {
    fun isValid(): Boolean {
        return MemberValidation.isValidLoginId(this.loginId)
                && MemberValidation.isValidPassword(this.password)
                && MemberValidation.isValidEmail(this.email)
                && MemberValidation.isValidPhoneNumber(this.phoneNumber)
    }
}