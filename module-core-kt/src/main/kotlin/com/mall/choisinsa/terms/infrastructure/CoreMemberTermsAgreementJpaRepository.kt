package com.mall.choisinsa.terms.infrastructure

import com.mall.choisinsa.terms.domain.MemberTermsAgreement
import com.mall.choisinsa.terms.domain.MemberTermsAgreementEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberTermsAgreementJpaRepository : JpaRepository<MemberTermsAgreementEntity, Long> {
    fun findAllByMemberIdAndMemberTermsIdIn(
        memberId: Long,
        memberTermsIds: List<Long>
    ): List<MemberTermsAgreement>

    fun deleteByMemberIdAndMemberTermsIdIn(
        memberId: Long,
        memberTermsIds: MutableList<Long>
    )
}