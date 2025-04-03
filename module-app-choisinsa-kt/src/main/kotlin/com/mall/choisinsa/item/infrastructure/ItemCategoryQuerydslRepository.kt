package com.mall.choisinsa.item.infrastructure

import com.mall.choisinsa.item.domain.dto.response.ItemCategoryWithItemCountResponse
import com.mall.choisinsa.item.domain.dto.search.ItemCategorySearch
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ItemCategoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findCategoriesWithItemCountBy(
        search: ItemCategorySearch
    ): List<ItemCategoryWithItemCountResponse> {

        queryFactory
            .select()


        return listOf()
    }

}