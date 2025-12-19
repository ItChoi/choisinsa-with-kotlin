package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.terms.fixture.request.MemberTermsRequestFixture
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.domain.dto.response.MemberResponse
import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest
import java.time.LocalDateTime

data class MemberFixture(
    var id: Long? = 0L,
    var loginType: LoginType = LoginType.HOME,
    var loginId: String = "test123",
    var password: String = "qwe123!!",
    var email: String = "test@test.com",
    var recommenderLoginId: String? = "test",
    var phoneNumber: String = "010-0000-0000",
    var ci: String? = null,
    var name: String,
    var nickName: String? = "둘리눈알",
    var birthday: String = "2000-09-22",
    var gender: GenderType = GenderType.SECRET,
    var createdDt: LocalDateTime? = LocalDateTime.now(),

    val memberTermsRequests: MutableList<MemberTermsRequest>? = MemberTermsRequestFixture().builds(),

) {
    fun request(): MemberRequest {
        return MemberRequest(
            loginId = this.loginId,
            password = this.password,
            email = this.email,
            recommenderLoginId = this.recommenderLoginId,
            phoneNumber = this.phoneNumber,
            ci = this.ci,
            memberTerms = this.memberTermsRequests,
            name = this.name,
            birthday = this.birthday,
        )
    }

    fun request(block: MemberFixture.() -> Unit): MemberRequest {
        val builder = MemberFixture(name = "최모씨")
        builder.apply(block)
        return builder.request()
    }

    fun response(): MemberResponse {
        return MemberResponse(
            id = this.id,
            loginId = this.loginId,
            name = this.name,
            nickName = this.nickName,
            email = this.email,
            phoneNumber = this.phoneNumber,
            birthday = this.birthday,
            gender = this.gender,
            createdDt = this.createdDt,
        )
    }

    fun response(block: MemberFixture.() -> Unit): MemberResponse {
        val builder = MemberFixture(name = "최모씨")
        builder.apply(block)
        return builder.response()
    }


}
