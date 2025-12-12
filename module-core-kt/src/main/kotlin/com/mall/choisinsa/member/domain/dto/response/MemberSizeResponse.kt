package com.mall.choisinsa.member.domain.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.MemberSizeType
import com.mall.choisinsa.member.domain.MemberBodyMeasurement
import java.time.LocalDateTime

data class MemberSizeResponse(
    val id: Long?,
    val memberId: Long,
    val type: MemberSizeType,
    val value: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdDt: LocalDateTime?
) {
    companion object {
        fun from(
            memberBodyMeasurement: MemberBodyMeasurement
        ): MemberSizeResponse {
            return MemberSizeResponse(
                id = memberBodyMeasurement.id,
                memberId = memberBodyMeasurement.memberId,
                type = memberBodyMeasurement.type,
                value = memberBodyMeasurement.value,
                createdDt = memberBodyMeasurement.createdDt,
            )
        }
    }
}
