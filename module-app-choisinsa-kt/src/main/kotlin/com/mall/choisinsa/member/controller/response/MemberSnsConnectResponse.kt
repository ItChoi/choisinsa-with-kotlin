package com.mall.choisinsa.member.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.member.domain.MemberSnsConnect
import java.time.LocalDateTime

data class MemberSnsConnectResponse(
    val id: Long,
    val memberId: Long,
    val snsId: String,
    val snsType: LoginType,
    val snsEmail: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdDt: LocalDateTime?,
) {
    companion object {
        fun from(
            memberSnsConnect: MemberSnsConnect
        ): MemberSnsConnectResponse {
            return MemberSnsConnectResponse(
                id = memberSnsConnect.id!!,
                memberId = memberSnsConnect.memberId,
                snsId = memberSnsConnect.snsId,
                snsType = memberSnsConnect.snsType,
                snsEmail = memberSnsConnect.snsEmail,
                createdDt = memberSnsConnect.createdDt,
            )
        }
    }
}
