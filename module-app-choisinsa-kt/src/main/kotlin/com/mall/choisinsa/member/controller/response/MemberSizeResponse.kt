package com.mall.choisinsa.member.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.MemberSizeType
import com.mall.choisinsa.member.domain.MemberSize
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
            memberSize: MemberSize
        ): MemberSizeResponse {
            return MemberSizeResponse(
                id = memberSize.id,
                memberId = memberSize.memberId,
                type = memberSize.type,
                value = memberSize.value,
                createdDt = memberSize.createdDt,
            )
        }
    }
}
