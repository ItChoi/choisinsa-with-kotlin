package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.MemberSnsConnect
import com.mall.choisinsa.member.service.port.CoreMemberSnsConnectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoreMemberSnsConnectService(
    private val coreMemberSnsConnectRepository: CoreMemberSnsConnectRepository,
) {

    @Transactional(readOnly = true)
    fun findAllMemberSnsConnectBy(
        memberId: Long
    ): List<MemberSnsConnect> {
        return coreMemberSnsConnectRepository.findAllByMemberId(memberId)
    }

}