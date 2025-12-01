package com.mall.choisinsa.brand.domain.dto.response

data class BrandResponse(
    var id: Long? = 0,
    var companyId: Long?,
    var companyName: String?,
    var nameEn: String,
    var nameKo: String,
    var brandDetailResponse: BrandDetailResponse,
)
