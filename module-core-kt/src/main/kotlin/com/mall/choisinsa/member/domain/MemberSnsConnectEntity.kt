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

) : BaseDateTimeEntity() {

}