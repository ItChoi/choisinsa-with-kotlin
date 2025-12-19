package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.model.MemberSnsConnect

interface CoreMemberSnsConnectRepository {
    fun findAllByMemberId(
        memberId: Long
    ): List<MemberSnsConnect>

}