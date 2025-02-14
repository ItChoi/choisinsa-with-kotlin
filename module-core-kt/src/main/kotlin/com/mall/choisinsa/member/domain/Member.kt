package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.common.port.ClockHolder
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import java.time.LocalDateTime

class Member(
    val id: Long? = null,
    val loginId: String,
    val password: String,
    val status: MemberStatus = MemberStatus.NORMAL,
    val name: String? = null,
    val email: String,
    val nickName: String? = null,
    val phoneNumber: String,
    val profileFileUrl: String? = null,
    val lastLoginDt: LocalDateTime? = null,

    val birthday: String? = null,
    val gender: GenderType = GenderType.NONE,
    val selfIntroduce: String? = null,

    val createdDt: LocalDateTime? = null,
    val updatedDt: LocalDateTime? = null,
) {
    companion object {
        fun from(request: MemberRequest): Member {
            return Member(
                loginId = request.loginId,
                password = request.password,
                email = request.email,
                phoneNumber = request.phoneNumber,
            )
        }

        fun login(clockHolder: ClockHolder) {
            // TODO: 시간을 파라미터로 받으면서 테스트가 가능해진다.

        }
    }
}