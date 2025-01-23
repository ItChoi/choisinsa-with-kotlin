package com.mall.choisinsa.member.service

import com.mall.choisinsa.mock.FakeMailSender
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CertificationServiceTest {

    @Test
    @DisplayName("")
    fun test() {
        // given
        val fakeMailSender = FakeMailSender()
        val certificationService = CertificationService(fakeMailSender)

        // when
        val email = "test@test.com"
        certificationService.send(email, 1L, "aaaaaaa-aaaaaa-aaaaa-aaaaa-aaaaaaa")

        // then
        Assertions.assertThat(fakeMailSender.email).isEqualTo(email)

    }

}