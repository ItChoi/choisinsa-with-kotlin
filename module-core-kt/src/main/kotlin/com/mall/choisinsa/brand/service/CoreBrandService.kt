package com.mall.choisinsa.brand.service

import com.mall.choisinsa.brand.domain.dto.response.SimpleBrandResponse
import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.mall.choisinsa.brand.service.port.CoreBrandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoreBrandService(
    private val coreBrandRepository: CoreBrandRepository,
) {

    @Transactional(readOnly = true)
    fun findAllSimpleBrandResponseBy(
        search: BrandSearch
    ): List<SimpleBrandResponse> {
        return coreBrandRepository.findAllSimpleBrandResponseBy(search)
    }
}