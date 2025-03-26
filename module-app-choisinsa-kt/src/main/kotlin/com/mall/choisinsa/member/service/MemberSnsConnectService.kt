package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.controller.response.MemberSnsConnectResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberSnsConnectService(
    private val coreMemberSnsConnectService: CoreMemberSnsConnectService,
) {

    @Transactional(readOnly = true)
    fun findAllMemberSnsConnectResponseBy(
        memberId: Long
    ): List<MemberSnsConnectResponse> {
        return coreMemberSnsConnectService.findAllMemberSnsConnectBy(memberId)
            .map { MemberSnsConnectResponse.from(it) }
    }

}