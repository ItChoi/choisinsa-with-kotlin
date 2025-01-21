package com.mall.choisinsa.web.provider

import com.mall.choisinsa.common.fixture.entity.member.MemberFixture
import com.mall.choisinsa.dto.global.MemberDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JwtTokenProviderTest {
//    @Value("\${jwt.access-token.secret}")
//    private val secretWithAccessToken: String = ""
//
//    @Value("\${jwt.access-token.validity-in-milliseconds}")
//    private val validityInMillisecondsWithAccessToken : Long = 0L
//
//    @Value("\$jwt.refresh-token.secret}")
//    private val secretWithRefreshToken: String = ""
//
//    @Value("\${jwt.refresh-token.validity-in-milliseconds}")
//    private val validityInMillisecondsWithRefreshToken : Long = 0L

    @Test
    @DisplayName("정상 토큰 생성")
    fun test() {
        // given
        val memberDto = MemberDto.of(MemberFixture().build { })
//        val jwtTokenProvider = JwtTokenProvider()
//        val generateToken = jwtTokenProvider.generateToken(memberDto.toTokenPayload(), memberDto.username)
//        println(generateToken)
        // when

        // then

    }
}