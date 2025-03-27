package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.member.domain.dto.response.*

data class MemberWrapperFixture(
    var member: MemberResponse = MemberFixture().response(),
    var memberSizes: List<MemberSizeResponse>? = listOf(MemberSizeFixture().response()),
    var memberAddress: MemberAddressResponse? = MemberAddressFixture().response(),
    var memberSnsConnects: List<MemberSnsConnectResponse>? = listOf(MemberSnsConnectFixture().response()),
) {
    fun response(): MemberWrapperResponse {
        return MemberWrapperResponse(
            member = this.member,
            memberSizes = this.memberSizes,
            memberAddress = this.memberAddress,
            memberSnsConnects = this.memberSnsConnects,
        )
    }
}
