package com.mall.choisinsa.member.model

import com.mall.choisinsa.common.enumeration.MemberCertificationStatus
import com.mall.choisinsa.common.enumeration.MemberCertificationType
import java.time.LocalDateTime

class MemberCertification(
    private val id: Long? = null,
    private val memberId: Long,
    private val type: MemberCertificationType,
    private val typeValue: String,
    private val validTime: LocalDateTime? = null,
    private var status: MemberCertificationStatus,
    private val description: String,
) {

}