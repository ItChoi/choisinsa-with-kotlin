package com.mall.choisinsa.domain.member

import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
class MemberSnsConnect(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    private val snsId: String,

    @Enumerated(EnumType.STRING)
    private val snsType: SnsType,

) : BaseDateTimeEntity() {

}