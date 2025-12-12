package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberCertificationStatus
import com.mall.choisinsa.common.enumeration.MemberCertificationType
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "member_certification")
@Entity
class MemberCertificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    @Enumerated(EnumType.STRING)
    private val type: MemberCertificationType,

    private val value: String,

    private val validDt: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    private var status: MemberCertificationStatus,

    private val desc: String,

    ) : BaseDateTimeEntity() {
}