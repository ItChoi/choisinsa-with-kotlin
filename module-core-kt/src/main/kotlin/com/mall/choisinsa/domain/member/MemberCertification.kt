package com.mall.choisinsa.domain.member

import com.mall.choisinsa.common.enumeration.MemberCertificationStatus
import com.mall.choisinsa.common.enumeration.MemberCertificationType
import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class MemberCertification(
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