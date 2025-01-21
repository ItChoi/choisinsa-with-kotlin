package com.mall.choisinsa.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.fixture.entity.member.MemberFixture
import com.mall.choisinsa.dto.request.member.LoginRequestDto
import com.mall.choisinsa.service.member.MemberService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TempMemberControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("")
    fun 로그인_성공시_토큰정보를_가져온다() {
        mockMvc.perform(post("/api/member/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(LoginRequestDto("test", "qwe123")))
        ).andExpect(MockMvcResultMatchers.status().isOk)

        // then

    }
}