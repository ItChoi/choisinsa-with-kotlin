package com.mall.choisinsa.brand.controller

import com.mall.choisinsa.brand.domain.dto.request.search.BrandSearch
import com.mall.choisinsa.brand.service.BrandService
import com.mall.choisinsa.common.controller.response.ResponseWrapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/brands")
class BrandController(
    private val brandService: BrandService,
) {

    @GetMapping("/with-item-count")
    fun getBrandsWithItemCount(
        @RequestBody search: BrandSearch,
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = brandService.findBrandsWithItemCountBy(search)
        )
    }
}