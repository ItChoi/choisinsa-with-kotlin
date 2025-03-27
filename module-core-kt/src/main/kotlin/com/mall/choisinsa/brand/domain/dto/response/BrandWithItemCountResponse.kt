package com.mall.choisinsa.brand.domain.dto.response

data class BrandWithItemCountResponse(
    val brandId: Long,
    val companyId: Long,
    val brandNameEn: String,
    val brandNameKo: String,
    val totalItemCount: Int,
)
