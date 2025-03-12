package com.mall.choisinsa.web.filter

import com.mall.choisinsa.common.enumeration.TokenType
import com.mall.choisinsa.common.domain.dto.AuthenticatedUser
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.member.service.SecurityService
import com.mall.choisinsa.common.exception.GlobalException
import com.mall.choisinsa.web.config.SecurityConfig
import com.mall.choisinsa.web.provider.JwtTokenProvider
import io.micrometer.common.util.StringUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val securityService: SecurityService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (excludedMatchers().any { it.matches(req) }) {
            filterChain.doFilter(req, res)
            return
        }

        val token = getTokenFromHeader(req)
        if (StringUtils.isBlank(token) || !jwtTokenProvider.isValidToken(TokenType.ACCESS_TOKEN, token)) {
            throw GlobalException(ExceptionType.INVALID_JWT_TOKEN)
        }

        val username = jwtTokenProvider.extractUsername(TokenType.ACCESS_TOKEN, token)
        val authenticatedUser: AuthenticatedUser = securityService.loadUserByLoginId(username)
        SecurityContextHolder.getContext().setAuthentication(
            authenticatedUser.toUsernamePasswordAuthenticationToken()
        );

        filterChain.doFilter(req, res);
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

    private fun excludedMatchers(): MutableList<AntPathRequestMatcher> {
        val unsecuredGetList = SecurityConfig.getUnsecuredHttpGetMethod().map {
            AntPathRequestMatcher(it, HttpMethod.GET.toString())
        }

        val unsecuredPostList = SecurityConfig.getUnsecuredHttpPostMethod().map {
            AntPathRequestMatcher(it, HttpMethod.POST.toString())
        }

        return mutableListOf<AntPathRequestMatcher>().apply {
            addAll(unsecuredGetList)
            addAll(unsecuredPostList)
        }
    }
}