package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.enumeration.SnsType
import com.mall.choisinsa.member.domain.dto.request.MemberPrivacySearchRequest
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.model.Member
import com.mall.choisinsa.member.model.MemberPrivacySearch
import com.mall.choisinsa.member.service.port.CoreMemberRepository
import com.mall.choisinsa.terms.service.CoreMemberTermsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CoreMemberService(
    private val coreMemberRepository: CoreMemberRepository,
    private val coreMemberTermsService: CoreMemberTermsService,
    private val memberPrivacySearchService: MemberPrivacySearchService,
    private val memberSnsConnectService: CoreMemberSnsConnectService,
) {

    @Transactional
    fun saveMember(
        request: MemberRequest,
        memberPrivacySearchRequest: MemberPrivacySearchRequest,
    ) {
        val member = coreMemberRepository.save(Member.from(request))
        memberPrivacySearchService.save(MemberPrivacySearch.of(
            member.id!!,
            request,
            memberPrivacySearchRequest)
        )

        coreMemberTermsService.saveAllWithMemberTermsAgreement(member.id!!, request.memberTerms)

    }

    fun existsBySnsIdAndSnsType(
        snsId: String?,
        snsType: SnsType
    ): Boolean =
        snsId?.let { memberSnsConnectService.existsBySnsIdAndSnsType(it, snsType) } ?: false


    fun isExistEmail(
        email: String?
    ): Boolean =
        email?.let { memberPrivacySearchService.existsByEmail(it) } ?: false
}