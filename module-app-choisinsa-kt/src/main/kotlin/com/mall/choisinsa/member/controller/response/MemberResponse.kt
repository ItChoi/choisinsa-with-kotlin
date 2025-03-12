package com.mall.choisinsa.member.controller.response

import com.mall.choisinsa.common.enumeration.GenderType

data class MemberResponse(
    var loginId: String,
    var name: String?,
    var nickName: String?,
    var email: String?,
    var phoneNumber: String?,
    var birthday: String?,
    var gender: GenderType,
    var memberSize: MemberSizeResponse?,
    var memberAddress: MemberAddressResponse?,
) {
    constructor() : this("", "", "", "", "", "", GenderType.NONE, null, null)
}
