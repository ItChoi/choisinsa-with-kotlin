package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.member.domain.dto.response.*

data class MemberWrapperFixture(
    var member: MemberResponse = MemberFixture(name = "최모씨").response(),
    var memberBodyMeasurements: List<MemberBodyMeasurementResponse>? = listOf(MemberSizeFixture().response()),
    var memberAddress: MemberAddressResponse? = MemberAddressFixture().response(),
    var memberSnsConnects: List<MemberSnsConnectResponse>? = listOf(MemberSnsConnectFixture().response()),
) {
    fun response(): MemberWrapperResponse {
        return MemberWrapperResponse(
            member = this.member,
            memberBodyMeasurements = this.memberBodyMeasurements,
            memberAddress = this.memberAddress,
            memberSnsConnects = this.memberSnsConnects,
        )
    }
}
