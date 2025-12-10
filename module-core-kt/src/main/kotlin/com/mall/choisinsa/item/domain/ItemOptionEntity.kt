package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "item_option")
class ItemOptionEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: ItemOptionEntity? = null,

    // 2. 부모 -> 자식: 여기가 중요합니다. 부모를 지우면 자식도 지워지게 설정합니다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    var children: MutableList<ItemOptionEntity> = mutableListOf(),

    var itemId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", insertable = false, updatable = false)
    var item: ItemEntity,

    var itemOptionTypeId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemOptionTypeId", insertable = false, updatable = false)
    var itemOptionType: ItemOptionTypeEntity,

    var name: String,

    var addPrice: Long,

    var stockQuantity: Int,

    var displayOrder: Int,

) : BaseDateTimeEntity()