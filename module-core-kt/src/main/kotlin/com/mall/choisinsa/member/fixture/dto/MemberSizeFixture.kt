package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.common.enumeration.MemberSizeType
import com.mall.choisinsa.member.domain.dto.response.MemberSizeResponse
import java.time.LocalDateTime

data class MemberSizeFixture(
    var id: Long? = 0L,
    var memberId: Long = 0L,
    var type: MemberSizeType = MemberSizeType.HEIGHT,
    var value: String = "177",
    var createdDt: LocalDateTime? = LocalDateTime.now()
) {
    fun response(): MemberSizeResponse {
        return MemberSizeResponse(
            id = this.id,
            memberId = this.memberId,
            type = this.type,
            value = this.value,
            createdDt = this.createdDt,
        )
    }

    fun response(block: MemberSizeFixture.() -> Unit): MemberSizeResponse {
        val builder = MemberSizeFixture()
        builder.apply(block)
        return builder.response()
    }
}
