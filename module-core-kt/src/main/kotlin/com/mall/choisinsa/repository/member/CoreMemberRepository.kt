package com.mall.choisinsa.repository.member

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberRepository : JpaRepository<Member, Long> {
    fun findByLoginIdAndStatus(loginId: String, status: MemberStatus): Member?

}