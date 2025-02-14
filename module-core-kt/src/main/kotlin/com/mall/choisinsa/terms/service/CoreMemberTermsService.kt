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

        val memberTermsIds = requests.map { request -> request.memberTermsId }
        validate(memberTermsIds, requests)

        val memberTermsAgreementsWithMemberTermsId = coreMemberTermsAgreementRepository.findAllBy(memberId, memberTermsIds)
            .associateBy { it.memberTermsId }

        val deleteMemberTermsIds: MutableList<Long> = mutableListOf()
        val saveMemberTermsAgreements: MutableList<MemberTermsAgreement> = mutableListOf()
        requests.forEach { request ->
            if (request.isAgree) {
                if (!memberTermsAgreementsWithMemberTermsId.containsKey(request.memberTermsId)) {
                    saveMemberTermsAgreements.add(MemberTermsAgreement(memberId = memberId, memberTermsId = request.memberTermsId))
                }
            } else {
                deleteMemberTermsIds.add(request.memberTermsId)
            }

            coreMemberTermsAgreementRepository.deleteBy(memberId, deleteMemberTermsIds)
            coreMemberTermsAgreementRepository.saveAll(saveMemberTermsAgreements)
        }
    }

    private fun validate(
        memberTermsIds: List<Long>,
        requests: List<MemberTermsRequest>
    ) {
        val memberTermsWithId = coreMemberTermsRepository.findAll(memberTermsIds)
            .associateBy { it.id }

        if (memberTermsIds.size !== memberTermsWithId.size) {
            throw GlobalException(ExceptionType.BAD_REQUEST)
        }

        requests.forEach { request ->
            val memberTerms = memberTermsWithId.get(request.memberTermsId);
            if (memberTerms!!.type === TermsType.REQUIRED && !request.isAgree) {
                throw GlobalException(ExceptionType.BAD_REQUEST)
            }
        }
    }

}