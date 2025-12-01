package com.mall.choisinsa.item.domain.dto.search

data class ItemCategorySearch(
    val depth: Int? = 1,
    val itemCategoryIds: List<Long>? = null,
    val itemCategoryName: String? = null,
)
