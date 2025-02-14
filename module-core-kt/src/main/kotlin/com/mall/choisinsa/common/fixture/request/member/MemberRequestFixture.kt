package com.mall.choisinsa.common.fixture.request.member

import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest

data class MemberRequestFixture(
    private val loginType: LoginType = LoginType.HOME,
    var loginId: String = "test123",
    var password: String = "qwe123!!",
    var email: String = "test@test.com",
    var recommenderLoginId: String? = "test",
    var phoneNumber: String = "010-0000-0000",
    var ci: String? = null,
    val memberTermsRequests: List<MemberTermsRequest>? = null,
) {
    fun build(): MemberRequest {
        return MemberRequest(
            loginId = this.loginId,
            password = this.password,
            email = this.email,
            recommenderLoginId = this.recommenderLoginId,
            phoneNumber = this.phoneNumber,
            ci = this.ci,
            memberTermsRequests = this.memberTermsRequests,
        )
    }

    fun build(block: MemberRequestFixture.() -> Unit): MemberRequest {
        val builder = MemberRequestFixture()
        builder.apply(block)
        return builder.build()
    }
}
