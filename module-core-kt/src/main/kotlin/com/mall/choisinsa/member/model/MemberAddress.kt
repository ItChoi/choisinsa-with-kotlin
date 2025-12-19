package com.mall.choisinsa.member.model

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
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