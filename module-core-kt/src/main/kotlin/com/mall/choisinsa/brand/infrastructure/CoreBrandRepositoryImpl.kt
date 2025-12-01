package com.mall.choisinsa.brand.infrastructure

import com.mall.choisinsa.brand.domain.dto.response.BrandResponse
import com.mall.choisinsa.brand.domain.dto.response.SimpleBrandResponse
import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.mall.choisinsa.brand.service.port.CoreBrandRepository
import org.springframework.stereotype.Repository

@Repository
class CoreBrandRepositoryImpl(
    private val coreBrandJpaRepository: CoreBrandJpaRepository,
    private val coreBrandQuerydslRepository: CoreBrandQuerydslRepository,
) : CoreBrandRepository {

    override fun findAllSimpleBrandResponseBy(
        search: BrandSearch
    ): List<SimpleBrandResponse> {
        return coreBrandQuerydslRepository.findAllSimpleBrandResponseBy(search)
    }
}