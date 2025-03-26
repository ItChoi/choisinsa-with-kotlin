package com.mall.choisinsa.terms.service

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.enumeration.terms.TermsType
import com.mall.choisinsa.common.exception.GlobalException
import com.mall.choisinsa.terms.domain.MemberTermsAgreement
import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest
import com.mall.choisinsa.terms.service.port.CoreMemberTermsAgreementRepository
import com.mall.choisinsa.terms.service.port.CoreMemberTermsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoreMemberTermsService(
    private val coreMemberTermsRepository: CoreMemberTermsRepository,
    private val coreMemberTermsAgreementRepository: CoreMemberTermsAgreementRepository

) {
    @Transactional
    fun saveAllWithMemberTermsAgreement(
        memberId: Long,
        requests: List<MemberTermsRequest>?,
    ) {
        if (requests.isNullOrEmpty()) return
        validate(requests)

        deleteMemberTermsAgreementBy(requests, memberId)
        coreMemberTermsAgreementRepository.saveAll(getMemberTermsAgreementForSave(requests, memberId))
    }

    private fun deleteMemberTermsAgreementBy(
        requests: List<MemberTermsRequest>,
        memberId: Long
    ) {
        val notAgreedMemberTermsIds = requests
            .filter { !it.isAgree }
            .map { it.memberTermsId }

        if (notAgreedMemberTermsIds.isNullOrEmpty()) return
        coreMemberTermsAgreementRepository.deleteBy(memberId, notAgreedMemberTermsIds)
    }

    private fun getMemberTermsAgreementForSave(
        requests: List<MemberTermsRequest>,
        memberId: Long
    ): List<MemberTermsAgreement> {
        val agreedMemberTermsIds: List<Long> = requests
            .filter { it.isAgree }
            .map { it.memberTermsId }

        val savedMemberTermsAgreementIds = coreMemberTermsAgreementRepository.findAllBy(memberId, agreedMemberTermsIds)
            .map { it.memberTermsId }

        return agreedMemberTermsIds
            .filterNot { savedMemberTermsAgreementIds.contains(it) }
            .map {
                MemberTermsAgreement(
                    memberId = memberId,
                    memberTermsId = it,
                )
            }
    }

    private fun validate(
        requests: List<MemberTermsRequest>
    ) {
        val memberTermsIds = requests.map { request -> request.memberTermsId }
        val memberTermsWithId = coreMemberTermsRepository.findAllByIdIn(memberTermsIds)
            .associateBy { it.id }

        if (memberTermsIds.size !== memberTermsWithId.size) {
            throw GlobalException(ExceptionType.BAD_REQUEST)
        }

        requests.forEach { request ->
            val memberTerms = memberTermsWithId.get(request.memberTermsId)
                ?: throw GlobalException(ExceptionType.BAD_REQUEST)

            if (memberTerms.type === TermsType.REQUIRED && !request.isAgree) {
                throw GlobalException(ExceptionType.REQUIRED_ITEM_NOT_PROVIDED)
            }
        }
    }

}