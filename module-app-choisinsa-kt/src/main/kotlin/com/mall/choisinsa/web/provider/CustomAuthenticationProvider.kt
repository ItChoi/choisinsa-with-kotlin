package com.mall.choisinsa.web.provider

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.service.member.SecurityService
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class CustomAuthenticationProvider(
    private val securityService: SecurityService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        //require(supports(authentication))

        val loginId = authentication.principal.toString()
        val password = authentication.credentials.toString()

        val memberDto = securityService.loadUserByUsername(loginId) as MemberDto
        if (!passwordEncoder.matches(password, memberDto.password)) {
            throw BadCredentialsException(ExceptionType.MISMATCH_REQUEST.msg);
        }

        memberDto.eraseCredentials()
        return UsernamePasswordAuthenticationToken(
            memberDto,
            memberDto.password,
            memberDto.authorities
        )
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication is UsernamePasswordAuthenticationToken
    }
}