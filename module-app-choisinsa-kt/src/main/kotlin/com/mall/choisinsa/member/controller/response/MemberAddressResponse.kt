package com.mall.choisinsa.member.controller.response

import com.mall.choisinsa.common.enumeration.MemberAddressStatus

data class MemberAddressResponse(
    val memberAddressString: MemberAddressStatus,
    val title: String,
    val address: String,
    val addressDetail: String,
)
