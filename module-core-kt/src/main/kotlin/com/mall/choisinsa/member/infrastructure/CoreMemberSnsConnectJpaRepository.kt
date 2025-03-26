package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberSnsConnectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberSnsConnectJpaRepository : JpaRepository<MemberSnsConnectEntity, Long> {
    fun findAllByMemberId(memberId: Long): List<MemberSnsConnectEntity>

}