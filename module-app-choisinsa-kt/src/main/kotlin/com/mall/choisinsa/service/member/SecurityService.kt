package com.mall.choisinsa.service.member

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.domain.member.Member
import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.repository.member.CoreMemberRepository
import com.mall.choisinsa.web.exception.GlobalException
import org.apache.coyote.BadRequestException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SecurityService(
    private val coreMemberRepository: CoreMemberRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        require(!MemberValidation.isValidLoginId(username))

        val member = findByLoginIdAndStatusOrThrow(username, MemberStatus.NORMAL)
        return MemberDto.of(member)
    }

    fun loadUserByLoginId(loginId: String): MemberDto {
        return loadUserByUsername(loginId) as MemberDto
    }

    @Transactional(readOnly = true)
    fun findByLoginIdAndStatusOrThrow(
        username: String,
        status: MemberStatus,
    ): Member {
        return coreMemberRepository.findByLoginIdAndStatus(username, status)
            ?: throw GlobalException(ExceptionType.NOT_FOUND_MEMBER)
    }
}