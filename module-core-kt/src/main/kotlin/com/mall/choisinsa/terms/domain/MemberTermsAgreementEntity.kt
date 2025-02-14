package com.mall.choisinsa.terms.domain

import com.mall.choisinsa.common.domain.CreateDateTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "member terms agreement")
@Entity
class MemberTermsAgreementEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val memberId: Long,

    val memberTermsId: Long,
) : CreateDateTimeEntity() {

    companion object {
        fun from(
            memberTermsAgreement: MemberTermsAgreement
        ): MemberTermsAgreementEntity {
            return MemberTermsAgreementEntity(
                id = memberTermsAgreement.id,
                memberId = memberTermsAgreement.memberId,
                memberTermsId = memberTermsAgreement.memberTermsId,
            )
        }
    }

    fun toModel(): MemberTermsAgreement {
        return MemberTermsAgreement(
            id = this.id,
            memberId = this.memberId,
            memberTermsId = this.memberTermsId,
        )
    }
}