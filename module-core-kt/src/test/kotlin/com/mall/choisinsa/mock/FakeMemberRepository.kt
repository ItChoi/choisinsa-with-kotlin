package com.mall.choisinsa.mock

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.member.model.Member
import com.mall.choisinsa.member.service.port.CoreMemberRepository

class FakeMemberRepository(
    val membersWithLoginId: MutableMap<String, Member>
) : CoreMemberRepository {
    override fun findByLoginIdAndStatus(loginId: String, status: MemberStatus): Member? {
        return membersWithLoginId[loginId]?.takeIf { it.status == MemberStatus.NORMAL }
    }

    override fun save(member: Member): Member {
        membersWithLoginId[member.loginId] = member
        return member
    }
}