package com.mall.choisinsa.web.config

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.member.service.SecurityService
import com.mall.choisinsa.web.exception.GlobalException
import com.mall.choisinsa.web.filter.JwtAuthenticationFilter
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.*

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
            //securityMatcher("/api/**")
            authorizeHttpRequests {
                getUnsecuredHttpGetMethod().forEach { authorize(HttpMethod.GET, it, permitAll) }
                getUnsecuredHttpPostMethod().forEach { authorize(HttpMethod.POST, it, permitAll) }

                authorize("/img/**", permitAll)
                authorize("/h2-console/**", permitAll)
                authorize("favicon.ico", permitAll)
                authorize("/css/**", permitAll)

                authorize(anyRequest, authenticated)
            }

            headers {
                frameOptions {
                    sameOrigin
                }
            }

            formLogin { disable() }
            csrf { disable() }
            //csrf { CsrfConfigurer<HttpSecurity>::disable }
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