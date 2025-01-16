package com.mall.choisinsa.service.member

import com.mall.choisinsa.common.util.MemberValidation
import com.mall.choisinsa.domain.member.Member
import com.mall.choisinsa.dto.global.MemberDto
import com.mall.choisinsa.repository.member.CoreMemberRepository
import org.apache.coyote.BadRequestException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SecurityService(
    private val coreMemberRepository: CoreMemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        require(!MemberValidation.isValidLoginId(username))

        val member = findByLoginIdOrThrow(username)
        return MemberDto.of(member)
    }

    @Transactional(readOnly = true)
    fun findByLoginIdOrThrow(username: String): Member {
        return coreMemberRepository.findByLoginId(username)
            ?: throw BadRequestException()
    }
}