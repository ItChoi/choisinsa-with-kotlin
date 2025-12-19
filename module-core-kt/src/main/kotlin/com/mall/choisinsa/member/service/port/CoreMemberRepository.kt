package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.member.model.Member

interface CoreMemberRepository {
    fun findByLoginIdAndStatus(
        loginId: String,
        status: MemberStatus
    ): Member?

    fun save(member: Member): Member

}