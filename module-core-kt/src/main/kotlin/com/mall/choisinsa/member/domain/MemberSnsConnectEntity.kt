package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.LoginType
import jakarta.persistence.*

@Table(name = "member_sns_connect")
@Entity
class MemberSnsConnectEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    private val snsId: String,

    @Enumerated(EnumType.STRING)
    private val snsType: LoginType,

    private val snsEmail: String,

) : BaseDateTimeEntity() {

    companion object {
        fun from(
            memberSnsConnect: MemberSnsConnect
        ): MemberSnsConnectEntity {
            return MemberSnsConnectEntity(
                id = memberSnsConnect.id,
                memberId = memberSnsConnect.memberId,
                snsId = memberSnsConnect.snsId,
                snsType = memberSnsConnect.snsType,
                snsEmail = memberSnsConnect.snsEmail,
            )
        }
    }

    fun toModel(): MemberSnsConnect {
        return MemberSnsConnect(
            id = this.id,
            memberId = this.memberId,
            snsId = this.snsId,
            snsType = this.snsType,
            snsEmail = this.snsEmail,
            createdDt = super.createdDt,
        )

    }
}