package com.mall.choisinsa.web.provider

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.domain.dto.AuthenticatedUser
import com.mall.choisinsa.member.service.SecurityService
import com.mall.choisinsa.web.exception.GlobalException
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class CustomAuthenticationProvider(
    private val securityService: SecurityService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    override fun authenticate(
        authentication: Authentication
    ): Authentication {
        require(supports(authentication::class.java))
        val authenticatedUser = loadUserByUsername(authentication)
        return authenticatedUser.toUsernamePasswordAuthenticationToken()
    }

    fun Authentication.toAuthenticatedUser(): AuthenticatedUser {
        return this.principal as AuthenticatedUser
    }


    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    private fun loadUserByUsername(authentication: Authentication): AuthenticatedUser {
        val loginId = authentication.principal.toString()
        val password = authentication.credentials.toString()

        val memberDto = securityService.loadUserByLoginId(loginId)
        if (!passwordEncoder.matches(password, memberDto.password)) {
            throw GlobalException(ExceptionType.MISMATCH_REQUEST);
        }

        return memberDto
    }
}