package com.mall.choisinsa.terms.service.port

import com.mall.choisinsa.terms.domain.MemberTermsAgreement

interface CoreMemberTermsAgreementRepository {
    fun save(
        memberId: Long,
        memberTermsId: Long
    ): MemberTermsAgreement

    fun findAllBy(
        memberId: Long,
        memberTermsIds: List<Long>
    ): List<MemberTermsAgreement>

    fun deleteBy(
        memberId: Long,
        memberTermsIds: List<Long>
    )
    fun saveAll(
        memberTermsAgreements: List<MemberTermsAgreement>
    )

}