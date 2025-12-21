package com.mall.choisinsa.item.controller

import com.mall.choisinsa.item.service.ItemService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/items")
class ItemController(
    private val itemService: ItemService,
) {

}