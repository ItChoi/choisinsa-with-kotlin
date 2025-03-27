package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.member.domain.dto.request.LoginRequest

data class LoginFixture(
    var loginId: String = "test123",
    var password: String = "qwe123!!",
) {
    fun build(): LoginRequest {
        return LoginRequest(
            loginId = this.loginId,
            password = this.password,
        )
    }

    fun build(block: LoginFixture.() -> Unit): LoginRequest {
        val builder = LoginFixture()
        builder.apply(block)
        return builder.build()
    }
}
