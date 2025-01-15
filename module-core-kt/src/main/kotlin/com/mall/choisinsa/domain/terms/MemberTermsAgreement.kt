package com.mall.choisinsa.domain.terms

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class MemberTermsAgreement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val memberId: Long,

    private val memberTermsId: Long,
) {

}