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
        /**
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