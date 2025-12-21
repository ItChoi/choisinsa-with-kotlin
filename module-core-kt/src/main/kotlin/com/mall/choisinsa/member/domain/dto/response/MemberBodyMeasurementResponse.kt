package com.mall.choisinsa.member.domain.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.MemberBodyMeasurementType
import com.mall.choisinsa.member.model.MemberBodyMeasurement
import java.time.LocalDateTime

data class MemberBodyMeasurementResponse(
    val id: Long?,
    val memberId: Long,
    val type: MemberBodyMeasurementType,
    val value: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdDt: LocalDateTime?
) {
    companion object {
        fun from(
            memberBodyMeasurement: MemberBodyMeasurement
        ): MemberBodyMeasurementResponse {
            return MemberBodyMeasurementResponse(
                id = memberBodyMeasurement.id,
                memberId = memberBodyMeasurement.memberId,
                type = memberBodyMeasurement.type,
                value = memberBodyMeasurement.value,
                createdDt = memberBodyMeasurement.createdDt,
            )
        }
    }
}
