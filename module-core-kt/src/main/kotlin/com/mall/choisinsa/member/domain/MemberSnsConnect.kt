package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.enumeration.LoginType
import java.time.LocalDateTime

class MemberSnsConnect(
    val id: Long? = null,
    val memberId: Long,
    val snsId: String,
    val snsType: LoginType,
    val snsEmail: String,
    val createdDt: LocalDateTime?,
)