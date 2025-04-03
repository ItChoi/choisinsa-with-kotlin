package com.mall.choisinsa.item.domain.dto.search

data class ItemCategorySearch(
    val depth: Long? = null,
    val itemCategoryIds: List<Long>? = null,
    val itemCategoryName: String? = null,
)
