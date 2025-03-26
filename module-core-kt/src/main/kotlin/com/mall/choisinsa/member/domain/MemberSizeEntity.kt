package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberSizeType
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "member_size")
class MemberSizeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    var memberId: Long,

    @Enumerated(EnumType.STRING)
    var type: MemberSizeType,

    var value: String,
) : BaseDateTimeEntity() {

    companion object {
        fun from(
            memberSize: MemberSize
        ): MemberSizeEntity {
            return MemberSizeEntity(
                id = memberSize.id,
                memberId = memberSize.memberId,
                type = memberSize.type,
                value = memberSize.value,
            )
        }
    }

    fun toModel(): MemberSize {
        return MemberSize(
            id = this.id,
            memberId = this.memberId,
            type = this.type,
            value = this.value,
            createdDt = super.createdDt,
        )
    }
}