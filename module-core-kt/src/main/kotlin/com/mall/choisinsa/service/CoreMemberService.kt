package com.mall.choisinsa.service

import com.mall.choisinsa.domain.member.MemberDetail
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.mall.choisinsa.repository.member.CoreMemberDetailRepository
import com.mall.choisinsa.repository.member.CoreMemberRepository
import org.springframework.stereotype.Service

@Service
class CoreMemberService(
    private val coreMemberRepository: CoreMemberRepository,
    private val coreMemberDetailRepository: CoreMemberDetailRepository,
) {
    fun saveMember(request: MemberRequestDto) {
        val member = coreMemberRepository.save(request.toEntity())
        coreMemberDetailRepository.save(MemberDetail.from(member.id!!))
    }
}