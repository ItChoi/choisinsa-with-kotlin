package com.mall.choisinsa.web.config

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.member.service.SecurityService
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
import java.time.LocalDateTime

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val securityService: SecurityService,
) {

    companion object {
        fun getUnsecuredHttpGetMethod(): List<String> {
            return listOf(
                "/h2-console",
                "/docs/**",
                "/img/**",
                "/h2-console/**",
                "/favicon.ico",
                "/css/**",
                "/api/members/*",
            )
        }

        fun getUnsecuredHttpPostMethod(): List<String> {
            return listOf(
                "/api/members/login",
                "/api/members",
            )
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { disable() }

            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }

            authorizeHttpRequests {
                getUnsecuredHttpGetMethod().forEach { authorize(HttpMethod.GET, it, permitAll) }
                getUnsecuredHttpPostMethod().forEach { authorize(HttpMethod.POST, it, permitAll) }

                authorize(anyRequest, authenticated)
            }

            headers {
                frameOptions {
                    sameOrigin
                }
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthenticationFilter(jwtTokenProvider, securityService))

        }//.apply { JwtAuthenticationFilter(jwtTokenProvider, securityService) }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}