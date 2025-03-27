package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.dto.response.MemberAddressResponse
import java.time.LocalDateTime

data class MemberAddressFixture(
    var id: Long? = 0L,
    var memberId: Long = 0L,
    var status: MemberAddressStatus = MemberAddressStatus.MAIN,
    var title: String = "우리집",
    var address: String = "서울 땡땡구 땡떙동",
    var addressDetail: String? = "303호",
    var createdDt: LocalDateTime? = LocalDateTime.now(),
) {
    fun response(): MemberAddressResponse {
        return MemberAddressResponse(
            id = this.id,
            memberId = this.memberId,
            status = this.status,
            title = this.title,
            address = this.address,
            addressDetail = this.addressDetail,
            createdDt = this.createdDt,
        )
    }

    fun response(block: MemberAddressFixture.() -> Unit): MemberAddressResponse {
        val builder = MemberAddressFixture()
        builder.apply(block)
        return builder.response()
    }
}
