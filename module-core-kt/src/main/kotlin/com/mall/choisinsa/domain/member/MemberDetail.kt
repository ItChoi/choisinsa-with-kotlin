package com.mall.choisinsa.domain.member

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.domain.common.BaseDateTimeEntity
import jakarta.persistence.*

@Entity
class MemberDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    private val birthday: String? = null,

    @Enumerated(EnumType.STRING)
    private val gender: GenderType = GenderType.NONE,

    private val height: Int? = null,

    private val weight: Int? = null,

    private val selfIntroduce: String? = null,

    private val recommenderMemberId: Long? = null,
) : BaseDateTimeEntity() {

    companion object {
        fun from(memberId: Long): MemberDetail {
            return MemberDetail(
                memberId = memberId
            )
        }
    }
}