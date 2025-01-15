package com.mall.choisinsa.api

import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.dto.wrapper.ResponseWrapper
import com.mall.choisinsa.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController (
    private val memberService: MemberService,
) {

    //@PostMapping("/login")

    @PostMapping
    fun saveMember(
        request: MemberRequestDto,
    ): ResponseWrapper {
        memberService.saveMember(request)
        return ResponseWrapper(HttpStatus.OK)
    }
}