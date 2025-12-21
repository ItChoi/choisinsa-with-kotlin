package com.mall.choisinsa.member.service

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.dto.response.MemberAddressResponse
import com.mall.choisinsa.member.service.port.MemberAddressRepository
import org.springframework.stereotype.Service

@Service
class MemberAddressService(
    private val memberAddressRepository: MemberAddressRepository,
) {
    fun findMainMemberAddressResponseBy(
        memberId: Long
    ): MemberAddressResponse? {
        return memberAddressRepository.findByMemberIdAndStatus(memberId, MemberAddressStatus.MAIN)
            ?.let { MemberAddressResponse.from(it) }
    }
}