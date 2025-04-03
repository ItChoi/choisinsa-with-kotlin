package com.mall.choisinsa.brand.domain.dto.response

data class BrandWithItemCountResponse(
    val id: Long,
    val companyId: Long,
    val nameEn: String,
    val nameKo: String,
    val totalItemCount: Long,
)
