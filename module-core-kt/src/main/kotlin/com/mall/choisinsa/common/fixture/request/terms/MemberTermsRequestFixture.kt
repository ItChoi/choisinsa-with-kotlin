package com.mall.choisinsa.common.fixture.request.terms

import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest

data class MemberTermsRequestFixture(
    var memberTermsId: Long = 1,
    var isAgree: Boolean = true,
) {

    fun build(): MemberTermsRequest {
        return MemberTermsRequest(
            memberTermsId = this.memberTermsId,
            isAgree = this.isAgree,
        )
    }

    fun build(block: MemberTermsRequestFixture.() -> Unit): MemberTermsRequest {
        val builder = MemberTermsRequestFixture()
        builder.block()
        return builder.build()
    }

    fun builds(): MutableList<MemberTermsRequest> {
        return mutableListOf(
            MemberTermsRequestFixture().build() {
                memberTermsId = 1L
                isAgree = true
            },
            MemberTermsRequestFixture().build() {
                memberTermsId = 2L
                isAgree = true
            },
            MemberTermsRequestFixture().build() {
                memberTermsId = 3L
                isAgree = false
            },
            MemberTermsRequestFixture().build() {
                memberTermsId = 4L
                isAgree = false
            },
        )
    }

}
