package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.CreateDateTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "item_category_closure")
class ItemCategoryClosureEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var ancestor: Long,

    var descendant: Long,

    var depth: Int,

    var displayOrder: Int,

) : CreateDateTimeEntity() {

}