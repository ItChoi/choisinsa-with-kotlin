package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberSizeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberSizeJpaRepository : JpaRepository<MemberSizeEntity, Long> {
    fun findAllByMemberId(memberId: Long): List<MemberSizeEntity>
}