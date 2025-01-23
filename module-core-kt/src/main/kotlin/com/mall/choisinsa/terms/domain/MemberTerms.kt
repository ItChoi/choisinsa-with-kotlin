package com.mall.choisinsa.terms.domain

import com.mall.choisinsa.common.enumeration.terms.TermsKind
import com.mall.choisinsa.common.enumeration.terms.TermsStatus
import com.mall.choisinsa.common.enumeration.terms.TermsType

class MemberTerms(
    private val id: Long? = null,
    private val type: TermsType,
    private val status: TermsStatus,
    private val kind: TermsKind,
    private val title: String,
    private val content: String,
) {

}