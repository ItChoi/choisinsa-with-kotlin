package com.mall.choisinsa.item.controller

import com.mall.choisinsa.item.service.ItemCategoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/item-categories")
class ItemCategoryController(
    private val itemCategoryService: ItemCategoryService,
) {

}