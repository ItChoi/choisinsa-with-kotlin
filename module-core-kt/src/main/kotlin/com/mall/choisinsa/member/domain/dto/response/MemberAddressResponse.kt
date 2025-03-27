package com.mall.choisinsa.member.domain.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.MemberAddress
import java.time.LocalDateTime

data class MemberAddressResponse(
    var id: Long? = null,
    var memberId: Long,
    var status: MemberAddressStatus,
    var title: String,
    var address: String,
    var addressDetail: String?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var createdDt: LocalDateTime?,
) {
    companion object {
        fun from(
            memberAddress: MemberAddress
        ): MemberAddressResponse {
            return MemberAddressResponse(
                id = memberAddress.id,
                memberId = memberAddress.memberId,
                status = memberAddress.status,
                title = memberAddress.title,
                address = memberAddress.address,
                addressDetail = memberAddress.addressDetail,
                createdDt = memberAddress.createdDt,
            )
        }
    }
}
