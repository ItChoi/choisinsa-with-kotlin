package com.mall.choisinsa.member.domain.dto.response

data class MemberWrapperResponse(
    var member: MemberResponse,
    //var memberSizes: List<MemberSizeResponse>?,
    var memberBodyMeasurements: List<MemberBodyMeasurementResponse>?,
    var memberAddress: MemberAddressResponse?,
    var memberSnsConnects: List<MemberSnsConnectResponse>?,
)
