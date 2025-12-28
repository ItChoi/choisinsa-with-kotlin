package com.mall.choisinsa.member.model

import com.mall.choisinsa.common.enumeration.SnsType
import java.time.LocalDateTime

class MemberSnsConnect(
    val id: Long? = null,
    val memberId: Long,
    val snsId: String,
    val snsType: SnsType,
    val snsEmail: String,
    val createdDt: LocalDateTime?,
)