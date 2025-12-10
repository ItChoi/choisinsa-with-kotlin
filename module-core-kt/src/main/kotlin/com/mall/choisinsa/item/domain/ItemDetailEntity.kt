package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "item_detail")
class ItemDetailEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var itemId: Long,

    var itemNumber: String?, // 품목 번호

    var materialName: String, // 제품 소재(면 100% 등)

    var manufacturer: String, // 제조사

    var manufacturerCountryName: String, // 제조국

    var manufacturingDate: LocalDateTime, // 제조연월

    var warrantyPeriod: LocalDateTime?, // 품질보증기간
) : BaseDateTimeEntity()