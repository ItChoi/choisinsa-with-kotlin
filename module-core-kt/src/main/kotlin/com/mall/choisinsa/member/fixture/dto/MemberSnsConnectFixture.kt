package com.mall.choisinsa.member.fixture.dto

import com.mall.choisinsa.common.enumeration.SnsType
import com.mall.choisinsa.member.domain.dto.response.MemberSnsConnectResponse
import java.time.LocalDateTime

data class MemberSnsConnectFixture(
    val id: Long = 0L,
    val memberId: Long = 0L,
    val snsId: String = "asdasdu14124hSDFDS3rfds",
    val snsType: SnsType = SnsType.APPLE,
    val snsEmail: String = "asdasd@sdFG2@apple.com",
    val createdDt: LocalDateTime? = LocalDateTime.now(),
) {
    fun response(): MemberSnsConnectResponse {
        return MemberSnsConnectResponse(
            id = this.id,
            memberId = this.memberId,
            snsId = this.snsId,
            snsType = this.snsType,
            snsEmail = this.snsEmail,
            createdDt = this.createdDt,
        )
    }

    fun response(block: MemberSnsConnectFixture.() -> Unit): MemberSnsConnectResponse {
        val builder = MemberSnsConnectFixture()
        builder.apply(block)
        return builder.response()
    }
}
