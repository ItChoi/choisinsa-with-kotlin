package com.mall.choisinsa.member.model

import com.mall.choisinsa.common.enumeration.MemberBodyMeasurementType
import java.time.LocalDateTime

class MemberBodyMeasurement(
    var id: Long? = null,
    var memberId: Long,
    var type: MemberBodyMeasurementType,
    var value: String,
    var createdDt: LocalDateTime?,
) {
}