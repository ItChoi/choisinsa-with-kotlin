package com.mall.choisinsa.admin_member.domain

import com.mall.choisinsa.common.enumeration.AdminMemberStatus
import com.mall.choisinsa.common.enumeration.AdminType
import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*

@Table(name = "admin_member")
@Entity
class AdminMemberEntity(
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