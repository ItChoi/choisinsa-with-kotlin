package com.mall.choisinsa.brand.domain.dto.response

data class BrandDetailResponse(
    var id: Long? = 0,
    var brandId: Long?,
    var logoFileUrl: String?,
    var logoFilename: String?,
    var description: String?,
    var webSite: String?,
    var email: String?,
    var address: String?,
    var managerPhoneNumber: String?,
)
