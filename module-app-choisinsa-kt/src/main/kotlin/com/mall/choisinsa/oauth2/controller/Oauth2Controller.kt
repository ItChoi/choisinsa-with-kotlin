package com.mall.choisinsa.oauth2.controller

import com.mall.choisinsa.common.controller.response.ResponseWrapper
import com.mall.choisinsa.common.enumeration.SnsType
import com.mall.choisinsa.oauth2.controller.request.Oauth2LoginRequestDto
import com.mall.choisinsa.oauth2.service.Oauth2Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping
@RestController
class Oauth2Controller(
    private val oauth2Service: Oauth2Service,
) {

    @GetMapping("/login/oauth2/code/{snsType}")
    fun login(
        @PathVariable snsType: SnsType,
        request: Oauth2LoginRequestDto,
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = oauth2Service.login(snsType, request)
        )
    }

}