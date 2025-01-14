package com.mall.choisinsa.domain.member

import com.mall.choisinsa.common.enumeration.AdminMemberStatus
import com.mall.choisinsa.common.enumeration.AdminType
import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
class AdminMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Enumerated(EnumType.STRING)
    private val adminType: AdminType,

    private val loginId: String,

    private val password: String,

    @Enumerated(EnumType.STRING)
    private val status: AdminMemberStatus,

    private val name: String? = null,

    private val email: String? = null,

    private val nickName: String? = null,

    private val phoneNumber: String? = null,

    ) : BaseDateTimeEntity() {
}