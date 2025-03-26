package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.MemberAddress
import com.mall.choisinsa.member.service.port.MemberAddressRepository
import org.springframework.stereotype.Repository

@Repository
class MemberAddressRepositoryImpl(
    private val memberAddressJpaRepository: MemberAddressJpaRepository,
) : MemberAddressRepository {

    override fun findByMemberIdAndStatus(
        memberId: Long,
        status: MemberAddressStatus
    ): MemberAddress? {
        return memberAddressJpaRepository.findByMemberIdAndStatus(memberId, status)
            ?.toModel()
    }
}