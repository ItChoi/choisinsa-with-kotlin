package com.mall.choisinsa.domain.member

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
class Member(
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
    ) : BaseDateTimeEntity() {

}