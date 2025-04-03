package com.mall.choisinsa.controller

import com.mall.choisinsa.ApiBaseTest
import com.mall.choisinsa.member.controller.MemberController
import com.mall.choisinsa.member.controller.response.TokenResponseDto
import com.mall.choisinsa.member.fixture.dto.LoginFixture
import com.mall.choisinsa.member.fixture.dto.MemberFixture
import com.mall.choisinsa.member.fixture.dto.MemberWrapperFixture
import com.mall.choisinsa.member.service.MemberService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(MemberController::class)
class MemberControllerTest(

) : ApiBaseTest() {

    @MockitoBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("일반 회원으로 가입한 유저가 로그인을 한다.")
    fun login() {
        // given
        val request = LoginFixture().build {
            loginId = "test"
            password = "qwe123"
        }

        val response = TokenResponseDto(
            "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwd3fgadHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzAkPbeyJhdXRob3JpdHkiOiJNRa3fgAIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE0MzYsImV4cCI6MTczNzA4MjMzNn0.gENhR-y7sKa5McT8dSbBx3FD2Mk8XxPpEU0SZjG3Fg9",
            "aBdFbGciOiJIUzI1NiJ9.eyJpZCI6MSwd3fgadHVzIjoiTk9STUFMIiwibmlja05hbWUiOiLrkZjrpqzriIjslYwiLCJsb2dpbklkIjoidGVzdCIsImF1dGhvcml0aWVzAkPbeyJhdXRob3JpdHkiOiJNRa3fgAIifV0sInN1YiI6InRlc3QiLCJpYXQiOjE3MzcwODE0MzYsImV4cCI6MTczNzA4MjMzNn0.gENhR-y7sKa5McT8dSbBx3FD2Mk8XxPpEU0SZj1A2Sf",
        )

        // when
        given(memberService.login(request))
            .willReturn(response)

        // then
        this.mockMvc.perform(post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk)
            .andDo(document("member_login",
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

    @Test
    @DisplayName("회원 가입을 한다.")
    fun saveMember() {
        // given
        val memberRequest = MemberFixture().request()

        // then
        this.mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequest)))
            .andExpect(status().isOk)
            .andDo(document("save_member",
                requestFields(
                    fieldWithPath("loginType").type(JsonFieldType.STRING).description("로그인 타입"),
                    fieldWithPath ("loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                    fieldWithPath ("password").type(JsonFieldType.STRING).description("비밀번호"),
                    fieldWithPath ("email").type(JsonFieldType.STRING).description("이메일"),
                    fieldWithPath ("recommenderLoginId").type(JsonFieldType.STRING).description("추천인 로그인 아이디").optional(),
                    fieldWithPath ("phoneNumber").type(JsonFieldType.STRING).description("").optional(),
                    fieldWithPath ("ci").type(JsonFieldType.STRING).description("휴대폰 고유 인증 값").optional(),
                    fieldWithPath ("memberTerms").type(JsonFieldType.ARRAY).description("회원 약관 ").optional(),
                    fieldWithPath ("memberTerms[].memberTermsId").type(JsonFieldType.NUMBER).description("회원 약관 ").optional(),
                    fieldWithPath ("memberTerms[].isAgree").type(JsonFieldType.BOOLEAN).description("회원 약관 ").optional(),
                ),
                responseFields(
                    fieldWithPath("status").description("http status"),
                    fieldWithPath("exceptionType").description("예외 타입"),
                    fieldWithPath("exceptionMsg").description("예외 메시지"),
                    fieldWithPath("data").description("응답 값"),
            )))
    }

    @Test
    @DisplayName("회원을 조회한다. 회원 데이터는 기본 회원 정보, 소셜 연동, 메인 배송지, 회원 사이즈 정보를 포함한다.")
    fun test() {
        // given
        val response = MemberWrapperFixture().response()

        // when
        val memberId = response.member.id
        given(memberService.findMemberWrapperResponseById(memberId!!))
            .willReturn(response)

        // then
        this.mockMvc.perform(get("/api/members/{memberId}", memberId))
            .andExpect(status().isOk)
            .andDo(document("get_member_wrapper",
                RequestDocumentation.pathParameters(
                    RequestDocumentation.parameterWithName("memberId").description("회원 ID"),
                ),
                responseFields(
                    fieldWithPath("status").type(JsonFieldType.STRING).description("응답 상태"),
                    fieldWithPath("exceptionType").type(JsonFieldType.NULL).description("예외 타입"),
                    fieldWithPath("exceptionMsg").type(JsonFieldType.NULL).description("예외 메시지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                    fieldWithPath("data.member").type(JsonFieldType.OBJECT).description("회원"),
                    fieldWithPath("data.member.id").type(JsonFieldType.NUMBER).description("회원 id"),
                    fieldWithPath("data.member.loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                    fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("이름").optional(),
                    fieldWithPath("data.member.nickName").type(JsonFieldType.STRING).description("닉네임").optional(),
                    fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("이메일"),
                    fieldWithPath("data.member.phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호").optional(),
                    fieldWithPath("data.member.birthday").type(JsonFieldType.STRING).description("생일").optional(),
                    fieldWithPath("data.member.gender").type(JsonFieldType.STRING).description("성별"),
                    fieldWithPath("data.member.createdDt").type(JsonFieldType.STRING).description("생성일"),

                    fieldWithPath("data.memberSizes").type(JsonFieldType.ARRAY).description("회원 사이즈"),
                    fieldWithPath("data.memberSizes[].id").type(JsonFieldType.NUMBER).description("사이즈 id"),
                    fieldWithPath("data.memberSizes[].memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                    fieldWithPath("data.memberSizes[].type").type(JsonFieldType.STRING).description("사이즈 타입"),
                    fieldWithPath("data.memberSizes[].value").type(JsonFieldType.STRING).description("값"),
                    fieldWithPath("data.memberSizes[].createdDt").type(JsonFieldType.STRING).description("생성일"),

                    fieldWithPath("data.memberAddress").type(JsonFieldType.OBJECT).description("메인 배송지"),
                    fieldWithPath("data.memberAddress.id").type(JsonFieldType.NUMBER).description("메인 배송지 id"),
                    fieldWithPath("data.memberAddress.memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                    fieldWithPath("data.memberAddress.status").type(JsonFieldType.STRING).description("배송지 상태"),
                    fieldWithPath("data.memberAddress.title").type(JsonFieldType.STRING).description("배송지 제목"),
                    fieldWithPath("data.memberAddress.address").type(JsonFieldType.STRING).description("배송지 주소"),
                    fieldWithPath("data.memberAddress.addressDetail").type(JsonFieldType.STRING).description("배송지 상세 주소").optional(),
                    fieldWithPath("data.memberAddress.createdDt").type(JsonFieldType.STRING).description("생성일"),

                    fieldWithPath("data.memberSnsConnects").type(JsonFieldType.ARRAY).description("소셜 연동"),
                    fieldWithPath("data.memberSnsConnects[].id").type(JsonFieldType.NUMBER).description("소셜 연동 id"),
                    fieldWithPath("data.memberSnsConnects[].memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                    fieldWithPath("data.memberSnsConnects[].snsId").type(JsonFieldType.STRING).description("소셜 id"),
                    fieldWithPath("data.memberSnsConnects[].snsType").type(JsonFieldType.STRING).description("소셜 타입"),
                    fieldWithPath("data.memberSnsConnects[].snsEmail").type(JsonFieldType.STRING).description("소셜 이메일"),
                    fieldWithPath("data.memberSnsConnects[].createdDt").type(JsonFieldType.STRING).description("생성일"),

                )
            ))
    }
}