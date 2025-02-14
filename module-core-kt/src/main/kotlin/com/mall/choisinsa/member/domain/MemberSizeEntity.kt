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

}