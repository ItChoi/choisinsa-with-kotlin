package com.mall.choisinsa.member.controller.response

import com.mall.choisinsa.common.enumeration.GenderType

data class MemberResponse(
    val loginId: String,
    val name: String?,
    val nickName: String?,
    val email: String?,
    val phoneNumber: String?,
    val birthday: String?,
    val gender: GenderType,
    val memberSize: MemberSizeResponse?,
    val memberAddress: MemberAddressResponse?,
)
