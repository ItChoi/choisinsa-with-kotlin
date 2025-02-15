package com.mall.choisinsa.terms.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.terms.TermsKind
import com.mall.choisinsa.common.enumeration.terms.TermsStatus
import com.mall.choisinsa.common.enumeration.terms.TermsType
import jakarta.persistence.*

@Table(name = "member_terms")
@Entity
class MemberTermsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val parentId: Long?,

    val depth: Int,

    val displayOrder: Int,

    @Enumerated(EnumType.STRING)
    val type: TermsType,

    @Enumerated(EnumType.STRING)
    val status: TermsStatus,

    @Enumerated(EnumType.STRING)
    val kind: TermsKind,

    val title: String,

    val content: String?,
) : BaseDateTimeEntity() {

    companion object {
        fun from(
            memberTerms: MemberTerms
        ): MemberTermsEntity {
            return MemberTermsEntity(
                id = memberTerms.id,
                parentId = memberTerms.parentId,
                depth = memberTerms.depth,
                displayOrder = memberTerms.displayOrder,
                type = memberTerms.type,
                status = memberTerms.status,
                kind = memberTerms.kind,
                title = memberTerms.title,
                content = memberTerms.content
            )
        }
    }

    fun toModel(): MemberTerms {
        return MemberTerms(
            id = this.id,
            parentId = this.parentId,
            depth = this.depth,
            displayOrder = this.displayOrder,
            type = this.type,
            status = this.status,
            kind = this.kind,
            title = this.title,
            content = this.content,
        )
    }

}