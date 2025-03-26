package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.enumeration.MemberSizeType
import java.time.LocalDateTime

class MemberSize(
    var id: Long? = null,
    var memberId: Long,
    var type: MemberSizeType,
    var value: String,
    var createdDt: LocalDateTime?,
) {
}