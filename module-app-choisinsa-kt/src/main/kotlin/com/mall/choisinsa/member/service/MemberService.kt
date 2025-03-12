package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.crypto.AesGcmCrypto
import com.mall.choisinsa.common.enumeration.RedisDataFormat
import com.mall.choisinsa.common.enumeration.RedisTTL
import com.mall.choisinsa.common.enumeration.TokenType
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.exception.GlobalException
import com.mall.choisinsa.member.controller.response.MemberResponse
import com.mall.choisinsa.member.controller.response.TokenResponseDto
import com.mall.choisinsa.member.domain.dto.request.LoginRequest
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.infrastructure.MemberQuerydslRepository
import com.mall.choisinsa.service.RedisService
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
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
    fun saveMember(request: MemberRequest) {
        validate(request)

        encodePrivacy(request)
        coreMemberService.saveMember(request)
    }

    @Transactional(readOnly = true)
    fun login(
        request: LoginRequest
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
        val redisTTL = when (tokenType) {
            TokenType.ACCESS_TOKEN -> RedisTTL.ACCESS_TOKEN_TTL
            TokenType.REFRESH_TOKEN -> RedisTTL.REFRESH_TOKEN_TTL
        }
        redisService.save(redisKey, token, redisTTL)
        return token
    }

    private fun isExistingMember(request: MemberRequest): Boolean {
        return memberQuerydslRepository.count(request) > 0
    }

    private fun validate(request: MemberRequest) {
        if (!request.isValid()) throw GlobalException(ExceptionType.INVALID_INPUT_FORMAT)
        if (isExistingMember(request)) throw GlobalException(ExceptionType.ALREADY_EXISTS_MEMBER)
    }

    private fun encodePrivacy(
        request: MemberRequest
    ) {
        request.password = passwordEncoder.encode(request.password)
        request.email = aesGcmCrypto.encrypt(request.email)
        request.phoneNumber = aesGcmCrypto.encrypt(request.phoneNumber)
    }

    @Transactional(readOnly = true)
    fun findMemberResponseById(
        memberId: Long
    ): MemberResponse {
        return memberQuerydslRepository.findMemberResponseById(memberId)
            ?: throw GlobalException(ExceptionType.NOT_FOUND_MEMBER)
    }
}

