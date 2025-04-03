package com.mall.choisinsa.brand.infrastructure

import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.mall.choisinsa.brand.domain.dto.response.BrandWithItemCountResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.mall.choisinsa.brand.domain.QBrandEntity.brandEntity as brand
import com.mall.choisinsa.item.domain.QItemEntity.itemEntity as item

@Repository
class BrandQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findBrandsWithItemCountBy(
        search: BrandSearch
    ): List<BrandWithItemCountResponse> {
        return queryFactory
            .select(
                Projections.constructor(
                    BrandWithItemCountResponse::class.java,
                    brand.id.`as`("brandId"),
                    brand.companyId.`as`("companyId"),
                    brand.nameEn.`as`("brandNameEn"),
                    brand.nameKo.`as`("brandNameKo"),
                    brand.count().`as`("totalItemCount"),
                )
            )
            .from(brand)
            .innerJoin(item)
            .on(item.brandId.eq(brand.id))
            .where(
                search.brandIds?.let { brand.id.`in`(search.brandIds) },
                search.companyId?.let { brand.companyId.eq(search.companyId) },
                search.brandNameEn?.let { brand.nameEn.eq(search.brandNameEn) },
                search.brandNameKo?.let { brand.nameKo.eq(search.brandNameKo) },
            )
            .groupBy(brand.id)
            .fetch()
    }

}