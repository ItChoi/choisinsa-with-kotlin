package com.mall.choisinsa.dto.request.member

import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.domain.member.Member
import java.security.InvalidParameterException

data class MemberRequestDto(
    private val loginType: LoginType = LoginType.HOME,
    val loginId: String,
    private val password: String,
    val email: String,
    val recommenderLoginId: String?,

    val phoneNumber: String,
    val ci: String,
) {
    init {
//        require(
//            MemberValidation.isValidLoginId(this.loginId)
//                    && MemberValidation.isValidPassword(this.password)
//                    && MemberValidation.isValidEmail(this.email)
//        )
    }
    fun toEntity(): Member {
        return Member(
            loginId = this.loginId,
            password = this.password,
            email = this.email,
            phoneNumber = this.phoneNumber
        )
    }

}