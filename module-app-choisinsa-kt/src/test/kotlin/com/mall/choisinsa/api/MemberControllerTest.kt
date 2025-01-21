package com.mall.choisinsa.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.fixture.request.member.LoginRequestFixture
import com.mall.choisinsa.service.member.MemberService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@MockBean(JpaMetamodelMappingContext::class)
@ExtendWith(RestDocumentationExtension::class)
@WebMvcTest(MemberController::class)
class MemberControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("홈페이지에서 일반 회원으로 가입한 유저의 로그인")
    fun login() {
        // given
        val request = LoginRequestFixture().build {
            loginId = "test"
            password = "qwe123"
        }

        // when


        this.mockMvc.perform(post("/api/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk)
            .andDo(document("home_member_login",
                requestFields(
                    fieldWithPath("loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                    fieldWithPath("password").type(JsonFieldType.STRING).description("로그인 아이디"),
                ),
                responseFields(
                    fieldWithPath("status").type(JsonFieldType.OBJECT).description("응답 상태"),
                    fieldWithPath("exceptionType").type(JsonFieldType.STRING).description("예외 타입"),
                    fieldWithPath("exceptionMsg").type(JsonFieldType.STRING).description("예외 메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                    fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("액세스 토큰"),
                    fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레시 토큰"),
                )
            ))

        // then

    }

}