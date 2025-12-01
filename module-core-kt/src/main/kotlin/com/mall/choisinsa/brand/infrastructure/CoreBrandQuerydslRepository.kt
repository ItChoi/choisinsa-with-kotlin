package com.mall.choisinsa.brand.infrastructure

import com.mall.choisinsa.brand.domain.dto.response.SimpleBrandResponse
import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

import com.mall.choisinsa.brand.domain.QBrandEntity.brandEntity as brand
import com.mall.choisinsa.brand.domain.QCompanyEntity.companyEntity as company

@Repository
class CoreBrandQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findAllSimpleBrandResponseBy(
        search: BrandSearch
    ): List<SimpleBrandResponse> {
        return queryFactory
            .select(
                Projections.fields(
                    SimpleBrandResponse::class.java,
                    brand.id,
                    brand.companyId,
                    brand.companyId,
                    brand.nameEn,
                    brand.nameKo,
                )
            )
            .from(brand)
            .innerJoin(company)
            .on(company.id.eq(brand.companyId))
            .where(
                search.brandIds?.let { brand.id.`in`(search.brandIds) },
                search.companyId?.let { brand.companyId.eq(search.companyId) },
                search.brandNameEn?.let { brand.nameEn.eq(search.brandNameEn) },
                search.brandNameKo?.let { brand.nameKo.eq(search.brandNameKo) },
            )
            .fetch()
    }
}