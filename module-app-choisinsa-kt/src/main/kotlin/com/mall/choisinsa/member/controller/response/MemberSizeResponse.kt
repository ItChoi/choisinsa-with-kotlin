package com.mall.choisinsa.member.controller.response

import com.mall.choisinsa.common.enumeration.MemberSizeType

data class MemberSizeResponse(
    val type: MemberSizeType,
    val value: String,
)
