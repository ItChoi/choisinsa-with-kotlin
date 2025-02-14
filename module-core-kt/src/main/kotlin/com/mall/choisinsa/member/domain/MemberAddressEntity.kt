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

}