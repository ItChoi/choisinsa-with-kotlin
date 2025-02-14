package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.GenderType
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

    val nickName: String? = null,

    @Enumerated(EnumType.STRING)
    private val gender: GenderType = GenderType.NONE,

    private val profileFileUrl: String? = null,

    private val selfIntroduce: String? = null,

    private val lastLoginDt: LocalDateTime? = null,

    private val birthday: String? = null,

    private val phoneNumber: String,

    private val name: String? = null,

    private val email: String,
) : BaseDateTimeEntity() {

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
                birthday = member.birthday,
                gender = member.gender,
                selfIntroduce = member.selfIntroduce
            )
        }
    }

    fun toModel(): Member {
        return Member(
            id = this.id,
            loginId = this.loginId,
            password = this.password,
            status = this.status,
            name = this.name,
            email = this.email,
            nickName = this.nickName,
            phoneNumber = this.phoneNumber,
            profileFileUrl = this.profileFileUrl,
            lastLoginDt = this.lastLoginDt,
            birthday = this.birthday,
            gender = this.gender,
            selfIntroduce = this.selfIntroduce,
            createdDt = this.createdDt,
            updatedDt = this.updatedDt,
        )
    }
}