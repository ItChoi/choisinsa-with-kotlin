package com.mall.choisinsa.web.filter

import com.mall.choisinsa.common.enumeration.TokenType
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.service.member.SecurityService
import com.mall.choisinsa.web.config.SecurityConfig
import com.mall.choisinsa.web.exception.GlobalException
import com.mall.choisinsa.web.provider.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val securityService: SecurityService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!SecurityConfig.isPermit(request.method, request.requestURI)) {
            val token = getTokenFromHeader(request)
            val username = jwtTokenProvider.extractUsername(TokenType.ACCESS_TOKEN, token!!)
            val memberDto: MemberDto = securityService.loadUserByLoginId(username)

            if (jwtTokenProvider.isTokenValid(TokenType.ACCESS_TOKEN, token, memberDto.username)) {
                SecurityContextHolder.getContext().setAuthentication(
                    memberDto.toUsernamePasswordAuthenticationToken()
                );
            }
        }

        filterChain.doFilter(request, response);
    }

    private fun getTokenFromHeader(request: HttpServletRequest): String {
        val AUTHORIZATION = "Authorization"
        val AUTHORIZATION_BEARER = "Bearer ";

        val authorization = request.getHeader(AUTHORIZATION)
        return authorization
            ?.takeIf { it.startsWith(AUTHORIZATION_BEARER) }
            ?.removePrefix(AUTHORIZATION_BEARER)
            ?: throw GlobalException(ExceptionType.INVALID_JWT_TOKEN)
    }
}