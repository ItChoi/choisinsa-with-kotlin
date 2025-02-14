package com.mall.choisinsa.terms.infrastructure

import com.mall.choisinsa.terms.domain.MemberTermsAgreement
import com.mall.choisinsa.terms.domain.MemberTermsAgreementEntity
import com.mall.choisinsa.terms.service.port.CoreMemberTermsAgreementRepository
import org.springframework.stereotype.Repository

@Repository
class CoreMemberTermsAgreementRepositoryImpl(
    private val coreMemberTermsAgreementJpaRepository: CoreMemberTermsAgreementJpaRepository,
) : CoreMemberTermsAgreementRepository {
    override fun save(
        memberId: Long,
        memberTermsId: Long
    ): MemberTermsAgreement {
        return coreMemberTermsAgreementJpaRepository.save(
            MemberTermsAgreementEntity(memberId = memberId, memberTermsId = memberTermsId)
        ).toModel()
    }

    override fun findAllBy(
        memberId: Long,
        memberTermsIds: List<Long>
    ): List<MemberTermsAgreement> {
        return coreMemberTermsAgreementJpaRepository.findAllByMemberIdAndMemberTermsIdIn(memberId, memberTermsIds)
    }

    override fun deleteBy(
        memberId: Long,
        memberTermsIds: MutableList<Long>
    ) {
        coreMemberTermsAgreementJpaRepository.deleteByMemberIdAndMemberTermsIdIn(memberId, memberTermsIds)
    }

    override fun saveAll(
        memberTermsAgreements: MutableList<MemberTermsAgreement>
    ) {
        val memberTermsAgreementEntities = memberTermsAgreements
            .asSequence()
            .map {MemberTermsAgreementEntity.from(it) }
            .toList()

        coreMemberTermsAgreementJpaRepository.saveAll(memberTermsAgreementEntities)
    }
}