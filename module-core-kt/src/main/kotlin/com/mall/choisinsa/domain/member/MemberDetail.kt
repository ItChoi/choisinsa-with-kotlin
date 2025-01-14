package com.mall.choisinsa.domain.member

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
class MemberDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    private val birthday: String? = null,

    @Enumerated(EnumType.STRING)
    private val gender: GenderType,

    private val height: Int? = null,

    private val weight: Int? = null,

    private val isAcceptMarketing: Boolean = false,

    private val isAuthenticateEmail: Boolean = false,

    private val isAuthenticatePhone: Boolean = false,

    private val selfIntroduce: String? = null,

    private val recommenderMemberId: Long? = null,
) : BaseDateTimeEntity() {

}