package com.mall.choisinsa.member.controller.response

data class MemberWrapperResponse(
    var member: MemberResponse,
    var memberSizes: List<MemberSizeResponse>?,
    var memberAddress: MemberAddressResponse?,
    var memberSnsConnects: List<MemberSnsConnectResponse>?,
)
