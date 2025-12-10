package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "item_option")
class ItemOptionTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var nameEn: String,

    var nameKo: String,

) : BaseDateTimeEntity()