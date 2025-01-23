package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "member")
@Entity
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val loginId: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val status: MemberStatus = MemberStatus.NORMAL,

    private val name: String? = null,

    private val email: String,

    val nickName: String? = null,

    private val phoneNumber: String,

    private val profileFileUrl: String? = null,

    private val lastLoginDt: LocalDateTime? = null,

    createdDt: LocalDateTime? = null,
    updatedDt: LocalDateTime? = null,
    ) : BaseDateTimeEntity(createdDt, updatedDt) {

    companion object {
        fun from(
            member: Member
        ): MemberEntity {
            return MemberEntity(
                id = member.id,
                loginId = member.loginId,
                password = member.password,
                status = member.status,
                name = member.name,
                email = member.email,
                nickName = member.nickName,
                phoneNumber = member.phoneNumber,
                profileFileUrl = member.profileFileUrl,
                lastLoginDt = member.lastLoginDt,
                createdDt = member.createdDt,
                updatedDt = member.updatedDt
            )
        }
    }

    fun toModel(): Member {
        return Member(
            id = id,
            loginId = loginId,
            password = password,
            status = status,
            name = name,
            email = email,
            nickName = nickName,
            phoneNumber = phoneNumber,
            profileFileUrl = profileFileUrl,
            lastLoginDt = lastLoginDt,
            createdDt = createdDt,
            updatedDt = updatedDt,
        )
    }
}