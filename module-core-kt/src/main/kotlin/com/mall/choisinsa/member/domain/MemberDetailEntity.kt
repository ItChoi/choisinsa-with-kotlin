package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.GenderType
import jakarta.persistence.*

@Table(name = "member_detail")
@Entity
class MemberDetailEntity(
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
        fun from(
            model: MemberDetail
        ): MemberDetailEntity {
            return MemberDetailEntity(
                id = model.id,
                memberId = model.memberId,
                birthday = model.birthday,
                gender = model.gender,
                height = model.height,
                weight = model.weight,
                selfIntroduce = model.selfIntroduce,
                recommenderMemberId = model.recommenderMemberId,
            )
        }
    }

    fun toModel(): MemberDetail {
        return MemberDetail(
            id = id,
            memberId = memberId,
            birthday = birthday,
            gender = gender,
            height = height,
            weight = weight,
            selfIntroduce = selfIntroduce,
            recommenderMemberId = recommenderMemberId,
        )
    }



}