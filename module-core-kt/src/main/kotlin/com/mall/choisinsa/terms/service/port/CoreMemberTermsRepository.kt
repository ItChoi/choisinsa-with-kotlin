package com.mall.choisinsa.terms.service.port

import com.mall.choisinsa.terms.domain.MemberTerms

interface CoreMemberTermsRepository {
    fun findAllByIdIn(memberTermsIds: List<Long>): List<MemberTerms>
}