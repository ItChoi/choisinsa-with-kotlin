package com.mall.choisinsa.brand.domain.dto.search

data class BrandSearch(
    val brandIds: List<Long>? = null,
    val companyId: Long? = null,
    val brandNameEn: String? = null,
    val brandNameKo: String? = null,
)
