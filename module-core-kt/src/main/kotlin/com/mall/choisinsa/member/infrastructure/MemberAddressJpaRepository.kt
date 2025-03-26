package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.MemberAddressEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberAddressJpaRepository : JpaRepository<MemberAddressEntity, Long> {
    fun findByMemberIdAndStatus(
        memberId: Long,
        status: MemberAddressStatus
    ): MemberAddressEntity?
}