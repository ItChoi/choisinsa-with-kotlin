package com.mall.choisinsa.service.member

import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.domain.member.MemberDetail
import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.dto.request.member.LoginRequestDto
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.dto.response.TokenResponseDto
import com.mall.choisinsa.repository.member.CoreMemberDetailRepository
import com.mall.choisinsa.repository.member.CoreMemberRepository
import com.mall.choisinsa.repository.member.MemberQuerydslRepository
import com.mall.choisinsa.service.CoreMemberService
import com.mall.choisinsa.web.provider.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.jaas.JaasGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService (
    private val coreMemberService: CoreMemberService,
    private val memberQuerydslRepository: MemberQuerydslRepository,
    private val authenticationProvider: AuthenticationProvider,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Transactional
    fun saveMember(request: MemberRequestDto) {
        // TODO("${request.ci} -> 레디스 저장, 정책 정의 후 적용")
        if (isExistingMember(request)) {
            throw IllegalArgumentException("이미 등록된 계정입니다.")
        }

        coreMemberService.saveMember(request)
    }

    private fun isExistingMember(request: MemberRequestDto): Boolean {
        return memberQuerydslRepository.count(request) > 0
    }

    @Transactional(readOnly = true)
    fun login(request: LoginRequestDto): TokenResponseDto {
        val memberDto = authenticationProvider.authenticate(
            UsernamePasswordAuthenticationToken(request.loginId, request.password)
        ).principal as MemberDto

        val refreshToken = ""

        return TokenResponseDto(
            jwtTokenProvider.generateToken(memberDto.toTokenPayload(), memberDto),
            refreshToken
        )
    }

}