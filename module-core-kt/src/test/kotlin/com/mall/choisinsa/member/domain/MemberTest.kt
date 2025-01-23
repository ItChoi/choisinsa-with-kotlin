package com.mall.choisinsa.member.domain

import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MemberTest {

    @Test
    @DisplayName("")
    fun MemberRequestDto_객체를_Member로_변환() {
        // given
        val request = MemberRequestDto(
            loginId = "test",
            password = "qwe123",
            email = "qwe123",
            phoneNumber = "qwe123",
        )

        // when
        val member = Member.from(request)

        // then
        assertThat(request.loginId).isEqualTo(member.loginId)
        assertThat(request.password).isEqualTo(member.password)
        assertThat(request.email).isEqualTo(member.email)
        assertThat(request.phoneNumber).isEqualTo(member.phoneNumber)
    }
}