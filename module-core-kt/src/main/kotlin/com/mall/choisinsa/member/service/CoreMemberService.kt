package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.Member
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.service.port.CoreMemberRepository
import com.mall.choisinsa.terms.service.CoreMemberTermsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoreMemberService(
    private val coreMemberRepository: CoreMemberRepository,
    private val coreMemberTermsService: CoreMemberTermsService,
) {

    @Transactional
    fun saveMember(request: MemberRequest) {
        val member = coreMemberRepository.save(Member.from(request))
        coreMemberTermsService.saveAllWithMemberTermsAgreement(member.id!!, request.memberTerms)
    }
}