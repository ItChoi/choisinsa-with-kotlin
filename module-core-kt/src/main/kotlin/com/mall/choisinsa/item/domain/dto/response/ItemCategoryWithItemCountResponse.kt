package com.mall.choisinsa.item.domain.dto.response

data class ItemCategoryWithItemCountResponse(
    val id: Long? = 0,
    val name: String? = null,
    val code: String? = null,
    val totalItemCount: Long? = 0,
)
