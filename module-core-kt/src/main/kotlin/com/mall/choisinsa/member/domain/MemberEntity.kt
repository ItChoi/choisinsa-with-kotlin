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

    var commonFileId: Long? = null,

    val loginId: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val status: MemberStatus = MemberStatus.NORMAL,

    val nickName: String? = null,

    @Enumerated(EnumType.STRING)
    private val gender: GenderType = GenderType.NONE,

    private val introduce: String? = null,

    private val lastLoginDt: LocalDateTime? = null,

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
                nickName = member.nickName,
                lastLoginDt = member.lastLoginDt,
                gender = member.gender,
                introduce = member.introduce
            )
        }
    }

    fun toModel(): Member {
        return Member(
            id = this.id,
            loginId = this.loginId,
            password = this.password,
            status = this.status,
            nickName = this.nickName,
            lastLoginDt = this.lastLoginDt,
            gender = this.gender,
            introduce = this.introduce,
            createdDt = this.createdDt,
            updatedDt = this.updatedDt,
        )
    }
}