package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberBodyMeasurementEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberSizeJpaRepository : JpaRepository<MemberBodyMeasurementEntity, Long> {
    fun findAllByMemberId(memberId: Long): List<MemberBodyMeasurementEntity>
}