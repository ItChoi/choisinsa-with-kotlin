package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "item")
class ItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var itemCategoryId: Long? = null,

    var brandId: Long,

    var status: String? = null,

    var targetType: String? = null,

    var nameEn: String? = null,

    var nameKo: String? = null,

    var price: Long? = null,

    var fileUrl: String? = null,

    var filename: String? = null,

    var totalStockQuantity: Int? = null,

) : BaseDateTimeEntity() {
}