package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import jakarta.persistence.*

@Entity
@Table(name = "member_address")
class MemberAddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var memberId: Long,

    @Enumerated(EnumType.STRING)
    var status: MemberAddressStatus,

    var title: String,

    var address: String,

    var addressDetail: String?,

) : BaseDateTimeEntity() {

    companion object {
        fun from(
            memberAddress: MemberAddress
        ): MemberAddressEntity {
            return MemberAddressEntity(
                id = memberAddress.id,
                memberId = memberAddress.memberId,
                status = memberAddress.status,
                title = memberAddress.title,
                address = memberAddress.address,
                addressDetail = memberAddress.addressDetail,
            )
        }
    }

    fun toModel(): MemberAddress {
        return MemberAddress(
            id = this.id,
            memberId = this.memberId,
            status = this.status,
            title = this.title,
            address = this.address,
            addressDetail = this.addressDetail,
            createdDt = super.createdDt,
        )
    }
}