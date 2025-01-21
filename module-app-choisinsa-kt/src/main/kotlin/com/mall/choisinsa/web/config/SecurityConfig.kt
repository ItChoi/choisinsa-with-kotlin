package com.mall.choisinsa.web.config

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.service.member.SecurityService
import com.mall.choisinsa.web.exception.GlobalException
import com.mall.choisinsa.web.filter.JwtAuthenticationFilter
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val securityService: SecurityService,
) {
    companion object {
        fun isPermit(
            httpMethod: String,
            requestUri: String,
         ): Boolean {
            return when (httpMethod.lowercase()) {
                "get" -> getUnsecuredHttpGetMethod().contains(requestUri)
                "post" -> getUnsecuredHttpPostMethod().contains(requestUri)
                else -> throw GlobalException(ExceptionType.BAD_REQUEST)
            }
        }

        fun getUnsecuredHttpGetMethod(): List<String> {
            return listOf(
                "/h2-console"
            )
        }

        fun getUnsecuredHttpPostMethod(): List<String> {
            return listOf(
                "/api/member/login",
                "/api/member"
            )
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeHttpRequests {
                getUnsecuredHttpGetMethod().forEach { authorize(HttpMethod.GET, it, permitAll) }
                getUnsecuredHttpPostMethod().forEach { authorize(HttpMethod.POST, it, permitAll) }
                authorize(anyRequest, authenticated)
            }

            formLogin {  }
            csrf { disable() }
            httpBasic { disable() }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthenticationFilter(jwtTokenProvider, securityService))
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}