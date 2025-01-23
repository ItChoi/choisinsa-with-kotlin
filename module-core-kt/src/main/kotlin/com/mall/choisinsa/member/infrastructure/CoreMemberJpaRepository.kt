package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.member.domain.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun findByLoginIdAndStatus(loginId: String, status: MemberStatus): MemberEntity?

}