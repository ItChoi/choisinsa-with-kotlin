package com.mall.choisinsa.terms.service.port

import com.mall.choisinsa.terms.domain.MemberTerms

interface CoreMemberTermsRepository {
    fun findAll(memberTermsIds: List<Long>): List<MemberTerms>
}