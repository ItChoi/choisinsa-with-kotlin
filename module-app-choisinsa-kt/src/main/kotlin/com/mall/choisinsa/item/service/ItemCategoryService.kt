package com.mall.choisinsa.item.service

import com.mall.choisinsa.item.domain.dto.response.ItemCategoryWithItemCountResponse
import com.mall.choisinsa.item.domain.dto.search.ItemCategorySearch
import com.mall.choisinsa.item.infrastructure.ItemCategoryQuerydslRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemCategoryService(
    private val itemCategoryQuerydslRepository: ItemCategoryQuerydslRepository,

) {

    @Transactional(readOnly = true)
    fun findCategoriesWithItemCountBy(
        search: ItemCategorySearch
    ): List<ItemCategoryWithItemCountResponse> {
        return itemCategoryQuerydslRepository.findAllItemCategoryWithItemCountResponseBy(search)
    }
}