package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.domain.dto.AuthenticatedUser
import com.mall.choisinsa.member.domain.dto.request.LoginRequestDto
import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import com.mall.choisinsa.member.controller.response.TokenResponseDto
import com.mall.choisinsa.member.infrastructure.MemberQuerydslRepository
import com.mall.choisinsa.member.service.CoreMemberService
import com.mall.choisinsa.web.exception.GlobalException
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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
) {

    @Transactional
    fun saveMember(request: MemberRequestDto) {
        // TODO("${request.ci} -> 레디스 저장, 정책 정의 후 적용")
        if (isExistingMember(request)) {
            throw GlobalException(ExceptionType.ALREADY_EXISTS_MEMBER)
        }

        request.password = encodePassword(request.password)
        coreMemberService.saveMember(request)
    }

    @Transactional(readOnly = true)
    fun login(
        request: LoginRequestDto
    ): TokenResponseDto {
        val authentication = authenticationProvider.authenticate(
            UsernamePasswordAuthenticationToken(request.loginId, request.password)
        )

        return jwtTokenProvider.generateToken(
            authentication,
            request.loginId
        )
    }

    private fun isExistingMember(request: MemberRequestDto): Boolean {
        return memberQuerydslRepository.
        count(request) > 0
    }

    private fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}

