package com.mall.choisinsa.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {

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


            //formLogin { disable() }
            csrf { disable() }
            httpBasic { disable() }
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    fun getUnsecuredHttpGetMethod(): List<String> {
        return listOf(

        )
    }

    private fun getUnsecuredHttpPostMethod(): List<String> {
        return listOf(
            "/api/member/login"
        )
    }
}