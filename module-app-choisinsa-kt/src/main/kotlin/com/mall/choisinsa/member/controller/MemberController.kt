package com.mall.choisinsa.member.controller

import com.mall.choisinsa.common.controller.response.ResponseWrapper
import com.mall.choisinsa.member.domain.dto.request.LoginRequestDto
import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import com.mall.choisinsa.member.service.MemberService
import org.springframework.web.bind.annotation.*

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
        // TODO("이름, 휴대폰번호, 이메일 개인정보 암호화")
        memberService.saveMember(request)
        return ResponseWrapper.ok()
    }
}