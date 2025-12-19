package com.mall.choisinsa.member.model

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.common.port.ClockHolder
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import java.time.LocalDateTime

class Member(
    val id: Long? = null,
    var commonFileId: Long? = null,

    var loginId: String,
    var password: String,
    var status: MemberStatus = MemberStatus.NORMAL,
    var nickName: String? = null,
    var lastLoginDt: LocalDateTime? = null,

    var gender: GenderType = GenderType.NONE,
    var introduce: String? = null,
    var createdDt: LocalDateTime? = null,
    var updatedDt: LocalDateTime? = null
    ) {
    companion object {
        fun from(request: MemberRequest): Member {
            return Member(
                loginId = request.loginId,
                password = request.password,
            )
        }

        fun login(clockHolder: ClockHolder) {
            // TODO: 시간을 파라미터로 받으면서 테스트가 가능해진다.

        }
    }
}