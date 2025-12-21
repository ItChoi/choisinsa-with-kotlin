package com.mall.choisinsa.item.controller

import com.mall.choisinsa.common.controller.response.ResponseWrapper
import com.mall.choisinsa.item.domain.dto.search.ItemCategorySearch
import com.mall.choisinsa.item.service.ItemCategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/item-categories")
class ItemCategoryController(
    private val itemCategoryService: ItemCategoryService,
) {
    @GetMapping("/with-item-count")
    fun getBrandsWithItemCount(
        @RequestBody search: ItemCategorySearch,
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = itemCategoryService.findCategoriesWithItemCountBy(search)
        )
    }
}