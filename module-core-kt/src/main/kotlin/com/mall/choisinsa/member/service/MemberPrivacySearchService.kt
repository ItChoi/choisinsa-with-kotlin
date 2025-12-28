package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.MemberPrivacySearchEntity
import com.mall.choisinsa.member.domain.dto.request.MemberPrivacySearchRequest
import com.mall.choisinsa.member.model.MemberPrivacySearch
import com.mall.choisinsa.member.service.port.MemberPrivacySearchRepository
import org.springframework.stereotype.Service

@Service
class MemberPrivacySearchService(
    private val memberPrivacySearchRepository: MemberPrivacySearchRepository,
) {

    fun save(
        memberPrivacySearch: MemberPrivacySearch,
    ) {
        memberPrivacySearchRepository.save(MemberPrivacySearchEntity.from(memberPrivacySearch))
    }

    fun existsByEmail(email: String): Boolean? {
        return memberPrivacySearchRepository.existsByEmail(email)
    }
}