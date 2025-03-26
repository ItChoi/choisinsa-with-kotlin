package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.domain.MemberSnsConnect

interface CoreMemberSnsConnectRepository {
    fun findAllByMemberId(
        memberId: Long
    ): List<MemberSnsConnect>

}