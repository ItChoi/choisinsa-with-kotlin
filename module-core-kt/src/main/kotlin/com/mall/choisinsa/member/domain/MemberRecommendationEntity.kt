package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.RecommendationType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "admin_member_privacy_search")
@Entity
class MemberRecommendationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var recommendationType: RecommendationType,

    var recommenderId: Long,

    var recommendedId: Long,

    var recommendedDt: LocalDateTime,

) : BaseDateTimeEntity()