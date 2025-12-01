package com.mall.choisinsa.item.infrastructure

import com.mall.choisinsa.item.domain.dto.response.ItemCategoryWithItemCountResponse
import com.mall.choisinsa.item.domain.dto.search.ItemCategorySearch
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

import com.mall.choisinsa.item.domain.QItemEntity.itemEntity as item
import com.mall.choisinsa.item.domain.QItemCategoryEntity.itemCategoryEntity as itemCategory
import com.mall.choisinsa.item.domain.QItemCategoryClosureEntity.itemCategoryClosureEntity as itemCategoryClosure

@Repository
class ItemCategoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findAllItemCategoryWithItemCountResponseBy(
        search: ItemCategorySearch
    ): List<ItemCategoryWithItemCountResponse> {
        return queryFactory
            .select(
                Projections.fields(
                    ItemCategoryWithItemCountResponse::class.java,
                    itemCategory.id,
                    itemCategory.name,
                    itemCategory.code,
                    itemCategory.count().`as`("totalItemCount"),
                )
            )
            .from(itemCategory)
            .innerJoin(itemCategoryClosure)
            .on(itemCategoryClosure.ancestor.eq(itemCategory.id))
            .innerJoin(item)
            .on(item.itemCategoryId.eq(itemCategoryClosure.descendant))
            .where(
                itemCategory.id.`in`(findAllIdByDept(search.depth))
            )
            .groupBy(itemCategory.id)
            .fetch()
    }

    private fun findAllIdByDept(
        depth: Int? = 1
    ): List<Long> {
        return queryFactory
            .select(itemCategoryClosure.ancestor)
            .from(itemCategoryClosure)
            .where(
                itemCategoryClosure.depth.eq(depth),
                itemCategoryClosure.ancestor.eq(itemCategoryClosure.descendant)
            )
            .fetch()
    }

}