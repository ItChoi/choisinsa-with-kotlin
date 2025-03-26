package com.mall.choisinsa.terms.infrastructure

import com.mall.choisinsa.terms.domain.MemberTerms
import com.mall.choisinsa.terms.service.port.CoreMemberTermsRepository
import org.springframework.stereotype.Repository

@Repository
class CoreMemberTermsRepositoryImpl(
    private val coreMemberTermsJpaRepository: CoreMemberTermsJpaRepository,
) : CoreMemberTermsRepository {
    override fun findAllByIdIn(memberTermsIds: List<Long>): List<MemberTerms> {
        return coreMemberTermsJpaRepository.findAllById(memberTermsIds).map { it.toModel() }
    }
}