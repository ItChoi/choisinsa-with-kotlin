package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.enumeration.GenderType

class MemberDetail(
    val id: Long? = null,
    val memberId: Long,
    val birthday: String? = null,
    val gender: GenderType = GenderType.NONE,
    val height: Int? = null,
    val weight: Int? = null,
    val selfIntroduce: String? = null,
    val recommenderMemberId: Long? = null,
) {
    companion object {
        fun from(memberId: Long): MemberDetail {
            return MemberDetail(
                memberId = memberId
            )
        }
    }

}