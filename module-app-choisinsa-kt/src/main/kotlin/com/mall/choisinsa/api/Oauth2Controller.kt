package com.mall.choisinsa.api

import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.dto.global.ResponseWrapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Oauth2Controller {

    @GetMapping("/login/oauth2/code/{loginType}")
    fun login(
        @PathVariable loginType: LoginType,
    ): ResponseWrapper {
        // TODO("프론트 설정 후 진행")
        return ResponseWrapper.ok(

        )
    }
}