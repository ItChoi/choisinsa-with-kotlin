package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.fixture.request.member.MemberRequestFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MemberTest {

    @Test
    @DisplayName("")
    fun MemberRequestDto_객체를_Member로_변환() {
        // given
        val fixture = MemberRequestFixture().build {
            loginId = "test"
            password = "qwe123"
            email = "qwe123"
            phoneNumber = "qwe123"
        }

        // when
        val member = Member.from(fixture)

        // then
        assertThat(fixture.loginId).isEqualTo(member.loginId)
        assertThat(fixture.password).isEqualTo(member.password)
        assertThat(fixture.email).isEqualTo(member.email)
        assertThat(fixture.phoneNumber).isEqualTo(member.phoneNumber)
    }
}