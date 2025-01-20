package com.mall.choisinsa.api

import com.mall.choisinsa.common.enumeration.LoginType
import com.mall.choisinsa.dto.global.ResponseWrapper
import com.mall.choisinsa.dto.request.member.LoginRequestDto
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.service.member.MemberService
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
        /**
         * TODO: 액세스 토큰이 유효한 시점에 새로 발급하는 경우 방지하기
         * eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwic3RhdHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJNRU1CRVIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE0MzYsImV4cCI6MTczNzA4MjMzNn0.gENhR-y7sKa5McT8dSbBx3FD2Mk8XxPpEU0SZjGf6WM
         * eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwic3RhdHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJNRU1CRVIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE5NTgsImV4cCI6MTczNzA4Mjg1OH0.LysVEVIay33Faq-SUHDJqVcZjdrHTQ6w9b50dIRPrVs
         */
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