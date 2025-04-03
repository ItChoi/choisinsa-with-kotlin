package com.mall.choisinsa.brand.service

import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.mall.choisinsa.brand.domain.dto.response.BrandWithItemCountResponse
import com.mall.choisinsa.brand.infrastructure.BrandQuerydslRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandService(
    private val brandQuerydslRepository: BrandQuerydslRepository,
) {

    @Transactional(readOnly = true)
    fun findBrandsWithItemCountBy(
        search: BrandSearch
    ): List<BrandWithItemCountResponse> {
        return brandQuerydslRepository.findBrandsWithItemCountBy(search)
    }
}