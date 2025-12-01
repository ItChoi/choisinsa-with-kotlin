package com.mall.choisinsa.brand.service.port

import com.mall.choisinsa.brand.domain.dto.response.SimpleBrandResponse
import com.mall.choisinsa.brand.domain.dto.search.BrandSearch

interface CoreBrandRepository {
    fun findAllSimpleBrandResponseBy(
        search: BrandSearch
    ): List<SimpleBrandResponse>
}