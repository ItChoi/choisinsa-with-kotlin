package com.mall.choisinsa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class TempMemberControllerTestEntity {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockitoBean
    private lateinit var memberService: MemberService

//    @Test
//    @DisplayName("")
//    fun 로그인_성공시_토큰정보를_가져온다() {
//        mockMvc.perform(post("/api/member/login")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(LoginRequestDto("test", "qwe123")))
//        ).andExpect(MockMvcResultMatchers.status().isOk)
//
//        // then
//    }
}