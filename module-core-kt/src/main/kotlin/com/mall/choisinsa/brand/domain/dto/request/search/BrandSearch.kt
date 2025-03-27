package com.mall.choisinsa.brand.domain.dto.request.search

data class BrandSearch(
    val brandIds: MutableList<Long>?,
    val companyId: Long?,
    val brandNameEn: String?,
    val brandNameKo: String?,
)
