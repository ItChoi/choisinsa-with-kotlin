package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.enumeration.LoginType

class MemberSnsConnect(
    private val id: Long? = null,
    private val memberId: Long,
    private val snsId: String,
    private val snsType: LoginType,
) {

}