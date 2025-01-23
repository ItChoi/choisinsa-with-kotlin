package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.Member
import com.mall.choisinsa.member.domain.MemberDetail
import com.mall.choisinsa.member.domain.MemberDetailEntity
import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import com.mall.choisinsa.member.infrastructure.CoreMemberJpaRepository
import com.mall.choisinsa.member.service.port.CoreMemberDetailRepository
import com.mall.choisinsa.member.service.port.CoreMemberRepository
import org.springframework.stereotype.Service

@Service
class CoreMemberService(
    //private val coreMemberRepository: CoreMemberJpaRepository,
    private val coreMemberRepository: CoreMemberRepository,
    private val coreMemberDetailRepository: CoreMemberDetailRepository,
) {
    fun saveMember(request: MemberRequestDto) {
        val member = coreMemberRepository.save(Member.from(request))
        coreMemberDetailRepository.save(MemberDetail.from(member.id!!))
    }
}