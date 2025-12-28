package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.enumeration.SnsType
import com.mall.choisinsa.member.model.MemberSnsConnect
import com.mall.choisinsa.member.service.port.CoreMemberSnsConnectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CoreMemberSnsConnectService(
    private val coreMemberSnsConnectRepository: CoreMemberSnsConnectRepository,
) {

    fun findAllMemberSnsConnectBy(
        memberId: Long
    ): List<MemberSnsConnect> {
        return coreMemberSnsConnectRepository.findAllByMemberId(memberId)
    }

    fun existsBySnsIdAndSnsType(
        snsId: String,
        snsType: SnsType
    ): Boolean {
        return coreMemberSnsConnectRepository.existsBySnsIdAndSnsType(snsId, snsType)
    }

}