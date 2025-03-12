package com.mall.choisinsa.controller

import com.mall.choisinsa.ApiBaseTest
import com.mall.choisinsa.common.fixture.request.member.LoginRequestFixture
import com.mall.choisinsa.member.controller.response.TokenResponseDto
import com.mall.choisinsa.member.service.MemberService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class MemberControllerTest(

) : ApiBaseTest() {

    @MockitoBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("홈페이지에서 일반 회원으로 가입한 유저의 로그인")
    fun login() {
        // given
        val request = LoginRequestFixture().build {
            loginId = "test"
            password = "qwe123"
        }

        val response = TokenResponseDto(
            "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwd3fgadHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzAkPbeyJhdXRob3JpdHkiOiJNRa3fgAIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE0MzYsImV4cCI6MTczNzA4MjMzNn0.gENhR-y7sKa5McT8dSbBx3FD2Mk8XxPpEU0SZjG3Fg9",
            "aBdFbGciOiJIUzI1NiJ9.eyJpZCI6MSwd3fgadHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzAkPbeyJhdXRob3JpdHkiOiJNRa3fgAIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE0MzYsImV4cCI6MTczNzA4MjMzNn0.gENhR-y7sKa5McT8dSbBx3FD2Mk8XxPpEU0SZj1A2Sf",
        )

        given(memberService.login(request))
            .willReturn(response)

        // when & then
        this.mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk)
            .andDo(document("home_member_login",
                requestFields(
                    fieldWithPath("loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                    fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 아이디"),
                ),
                responseFields(
                    fieldWithPath("status").type(JsonFieldType.STRING).description("응답 상태"),
                    fieldWithPath("exceptionType").type(JsonFieldType.NULL).description("예외 타입"),
                    fieldWithPath("exceptionMsg").type(JsonFieldType.NULL).description("예외 메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                    fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("액세스 토큰"),
                    fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레시 토큰"),
                )
            ))
    }
}