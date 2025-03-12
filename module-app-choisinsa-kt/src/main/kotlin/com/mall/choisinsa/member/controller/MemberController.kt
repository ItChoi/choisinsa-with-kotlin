package com.mall.choisinsa.member.controller

import com.mall.choisinsa.common.controller.response.ResponseWrapper
import com.mall.choisinsa.member.domain.dto.request.LoginRequest
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberController (
    private val memberService: MemberService,
) {


    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = memberService.login(request)
        )
    }

    @PostMapping
    fun saveMember(
        @RequestBody request: MemberRequest,
    ): ResponseWrapper {
        memberService.saveMember(request)
        return ResponseWrapper.ok()
    }

    @GetMapping("/{memberId}")
    fun getMember(
        @PathVariable memberId: Long,
    ): ResponseWrapper {
        return ResponseWrapper.ok(
            data = memberService.findMemberResponseById(memberId)
        )
    }
}