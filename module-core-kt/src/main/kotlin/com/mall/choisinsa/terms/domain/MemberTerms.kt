package com.mall.choisinsa.terms.domain

import com.mall.choisinsa.common.enumeration.terms.TermsKind
import com.mall.choisinsa.common.enumeration.terms.TermsStatus
import com.mall.choisinsa.common.enumeration.terms.TermsType

class MemberTerms(
    val id: Long? = null,
    val parentId: Long?,
    val depth: Int,
    val displayOrder: Int,
    val type: TermsType,
    val status: TermsStatus,
    val kind: TermsKind,
    val title: String,
    val content: String,
) {

}