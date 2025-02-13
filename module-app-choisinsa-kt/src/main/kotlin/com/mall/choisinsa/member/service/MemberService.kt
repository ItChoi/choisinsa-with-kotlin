package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.crypto.AesGcmCrypto
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.enumeration.RedisDataFormat
import com.mall.choisinsa.common.enumeration.TokenType
import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.member.domain.dto.request.LoginRequestDto
import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import com.mall.choisinsa.member.controller.response.TokenResponseDto
import com.mall.choisinsa.member.infrastructure.MemberQuerydslRepository
import com.mall.choisinsa.service.RedisService
import com.mall.choisinsa.web.exception.GlobalException
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.codec.Utf8
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class MemberService (
    private val coreMemberService: CoreMemberService,
    private val memberQuerydslRepository: MemberQuerydslRepository,
    private val authenticationProvider: AuthenticationProvider,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val redisService: RedisService,
    private val aesGcmCrypto: AesGcmCrypto,
) {

    @Transactional
    fun saveMember(request: MemberRequestDto) {
        require(request.isValid()) { GlobalException(ExceptionType.INVALID_REQUEST) }
        if (isExistingMember(request)) {
            throw GlobalException(ExceptionType.ALREADY_EXISTS_MEMBER)
        }

        //request.password = encodePassword(request.password)
        encodePrivacy(request)
        coreMemberService.saveMember(request)
    }

    private fun encodePrivacy(
        request: MemberRequestDto
    ) {
        request.password = passwordEncoder.encode(request.password)
        request.email = aesGcmCrypto.encrypt(request.email)
        request.phoneNumber = aesGcmCrypto.encrypt(request.phoneNumber)
    }

    @Transactional(readOnly = true)
    fun login(
        request: LoginRequestDto
    ): TokenResponseDto {
        val loginId = request.loginId
        val authentication = authenticationProvider.authenticate(
            UsernamePasswordAuthenticationToken(loginId, request.password)
        )

        return generateTokenResponseDto(authentication, loginId)
    }

    private fun generateTokenResponseDto(
        authentication: Authentication,
        loginId: String
    ): TokenResponseDto {
        val accessToken = getOrGenerateToken(TokenType.ACCESS_TOKEN, authentication, loginId)
        val refreshToken = getOrGenerateToken(TokenType.REFRESH_TOKEN, authentication, loginId)

        return TokenResponseDto(accessToken, refreshToken)
    }

    private fun getOrGenerateToken(
        tokenType: TokenType,
        authentication: Authentication,
        loginId: String
    ): String {
        val redisKey = getRedisKeyForTokenType(tokenType, loginId)

        return redisService.get(redisKey) ?: generateAndSaveToken(redisKey, tokenType, authentication, loginId)
    }

    private fun getRedisKeyForTokenType(
        tokenType: TokenType,
        loginId: String
    ): String {
        return when (tokenType) {
            TokenType.ACCESS_TOKEN -> RedisDataFormat.ACCESS_TOKEN.formattedKey(loginId)
            TokenType.REFRESH_TOKEN -> RedisDataFormat.REFRESH_TOKEN.formattedKey(loginId)
        }
    }

    private fun generateAndSaveToken(
        redisKey: String,
        tokenType: TokenType,
        authentication: Authentication,
        loginId: String
    ): String {
        val token = jwtTokenProvider.generateToken(tokenType, authentication, loginId)
        redisService.save(redisKey, token)
        return token
    }

    private fun isExistingMember(request: MemberRequestDto): Boolean {
        return memberQuerydslRepository.count(request) > 0
    }

    private fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}

