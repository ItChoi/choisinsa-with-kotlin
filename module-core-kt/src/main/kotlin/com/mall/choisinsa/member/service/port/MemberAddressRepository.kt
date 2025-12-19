package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.model.MemberAddress

interface MemberAddressRepository {
    fun findByMemberIdAndStatus(
        memberId: Long,
        main: MemberAddressStatus
    ): MemberAddress?
}