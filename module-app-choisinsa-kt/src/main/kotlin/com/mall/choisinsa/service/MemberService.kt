package com.mall.choisinsa.service

import com.mall.choisinsa.domain.member.MemberDetail
import com.mall.choisinsa.dto.MemberDto
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.repository.member.MemberDetailRepository
import com.mall.choisinsa.repository.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberDetailRepository: MemberDetailRepository,

) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        return MemberDto("asd")
    }

    @Transactional
    fun saveMember(request: MemberRequestDto) {
        TODO("${request.ci} -> 레디스 저장, 정책 정의 후 적용")



        //request

        val member = memberRepository.save(request.toEntity())
        memberDetailRepository.save(MemberDetail.from(member.id!!))
    }

    private fun isExistingMember(request: MemberRequestDto): Boolean {

        return false
    }

}