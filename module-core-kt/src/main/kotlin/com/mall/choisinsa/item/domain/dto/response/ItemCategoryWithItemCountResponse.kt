package com.mall.choisinsa.item.domain.dto.response

data class ItemCategoryWithItemCountResponse(
    val id: Long,
    val code: String,
    val name: String,
    val depth: Int,
    val displayOrder: Int,
    val totalItemCount: Long,
)
