package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberPrivacySearchEntity
import com.mall.choisinsa.member.service.port.MemberPrivacySearchRepository
import org.springframework.stereotype.Repository

@Repository
class MemberPrivacySearchRepositoryImpl(
    private val memberPrivacySearchJpaRepository: MemberPrivacySearchJpaRepository,
) : MemberPrivacySearchRepository {
    override fun save(
        memberPrivacySearchEntity: MemberPrivacySearchEntity
    ) {
        memberPrivacySearchJpaRepository.save(memberPrivacySearchEntity)
    }
}