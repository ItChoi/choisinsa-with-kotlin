package com.mall.choisinsa.domain.terms

import com.mall.choisinsa.common.enumeration.terms.TermsKind
import com.mall.choisinsa.common.enumeration.terms.TermsStatus
import com.mall.choisinsa.common.enumeration.terms.TermsType
import jakarta.persistence.*

@Entity
class MemberTerms(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Enumerated(EnumType.STRING)
    private val type: TermsType,

    @Enumerated(EnumType.STRING)
    private val status: TermsStatus,

    @Enumerated(EnumType.STRING)
    private val kind: TermsKind,

    private val title: String,

    private val content: String,
) {

}