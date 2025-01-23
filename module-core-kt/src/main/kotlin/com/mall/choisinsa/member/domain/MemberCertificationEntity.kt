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

    private val typeValue: String,

    private val validTime: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    private var status: MemberCertificationStatus,

    private val description: String,

    ) : BaseDateTimeEntity() {
}