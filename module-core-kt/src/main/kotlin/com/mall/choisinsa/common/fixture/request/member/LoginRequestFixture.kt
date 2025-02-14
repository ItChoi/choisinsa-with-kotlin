package com.mall.choisinsa.common.fixture.request.member

import com.mall.choisinsa.member.domain.dto.request.LoginRequest

data class LoginRequestFixture(
    var loginId: String = "test123",
    var password: String = "qwe123!!",
) {
    fun build(): LoginRequest {
        return LoginRequest(
            loginId = this.loginId,
            password = this.password,
        )
    }

    fun build(block: LoginRequestFixture.() -> Unit): LoginRequest {
        val builder = LoginRequestFixture()
        builder.apply(block)
        return builder.build()
    }
}
