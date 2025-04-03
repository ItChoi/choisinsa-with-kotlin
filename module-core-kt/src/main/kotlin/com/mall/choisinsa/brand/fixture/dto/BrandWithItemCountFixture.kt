package com.mall.choisinsa.brand.fixture.dto

import com.mall.choisinsa.brand.domain.dto.response.BrandWithItemCountResponse

data class BrandWithItemCountFixture(
    var id: Long = 0L,
    var companyId: Long = 0L,
    var nameEn: String = "Nike",
    var nameKo: String = "나이키",
    var totalItemCount: Long = 1231,
) {
    fun response(): BrandWithItemCountResponse {
        return BrandWithItemCountResponse(
            id = this.id,
            companyId = this.companyId,
            nameEn = this.nameEn,
            nameKo = this.nameKo,
            totalItemCount = this.totalItemCount,
        )
    }

    fun response(
        block: BrandWithItemCountFixture.() -> Unit
    ): BrandWithItemCountResponse {
        val builder = BrandWithItemCountFixture()
        builder.apply(block)
        return builder.response()
    }
}
