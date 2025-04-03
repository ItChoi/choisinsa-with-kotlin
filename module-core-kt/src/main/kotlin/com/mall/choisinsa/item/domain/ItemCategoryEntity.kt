package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "item_category")
class ItemCategoryEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var code: String? = null,

) : BaseDateTimeEntity() {

}