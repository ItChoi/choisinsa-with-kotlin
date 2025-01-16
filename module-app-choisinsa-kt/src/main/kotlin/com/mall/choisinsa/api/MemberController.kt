package com.mall.choisinsa.api

import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.dto.global.ResponseWrapper
import com.mall.choisinsa.dto.request.member.LoginRequestDto
import com.mall.choisinsa.service.member.MemberService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController (
    private val memberService: MemberService,
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequestDto
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = memberService.login(request)
        )
    }

    @PostMapping
    fun saveMember(
        @RequestBody request: MemberRequestDto,
    ): ResponseWrapper {
        memberService.saveMember(request)
        return ResponseWrapper.ok()
    }
}