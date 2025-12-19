package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.domain.dto.AuthenticatedUser
import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.member.model.Member
import com.mall.choisinsa.member.service.port.CoreMemberRepository
import com.mall.choisinsa.common.exception.GlobalException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SecurityService(
    private val coreMemberRepository: CoreMemberRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        require(MemberValidation.isValidLoginId(username))

        val member = findByLoginIdAndStatusOrThrow(username, MemberStatus.NORMAL)
        return AuthenticatedUser.of(member)
    }

    fun loadUserByLoginId(loginId: String): AuthenticatedUser {
        return loadUserByUsername(loginId) as AuthenticatedUser
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