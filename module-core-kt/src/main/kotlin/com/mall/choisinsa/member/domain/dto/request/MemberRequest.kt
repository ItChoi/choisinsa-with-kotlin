package com.mall.choisinsa.member.domain.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest

data class MemberRequest(
    val loginId: String,
    var password: String,
    var name: String,
    var email: String,
    var birthday: String,
    val recommenderLoginId: String? = null,
    var phoneNumber: String,
    val ci: String? = null,
    val memberTerms: MutableList<MemberTermsRequest>?,
) {

    @JsonIgnore
    fun isValid(): Boolean {
        return MemberValidation.isValidLoginId(this.loginId)
                && MemberValidation.isValidPassword(this.password)
                && MemberValidation.isValidEmail(this.email)
                && MemberValidation.isValidPhoneNumber(this.phoneNumber)
    }
}