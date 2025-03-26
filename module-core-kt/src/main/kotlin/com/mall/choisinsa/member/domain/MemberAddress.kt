package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import jakarta.persistence.*
import java.time.LocalDateTime

class MemberAddress(
    var id: Long? = null,
    var memberId: Long,
    var status: MemberAddressStatus,
    var title: String,
    var address: String,
    var addressDetail: String?,
    var createdDt: LocalDateTime?,
)