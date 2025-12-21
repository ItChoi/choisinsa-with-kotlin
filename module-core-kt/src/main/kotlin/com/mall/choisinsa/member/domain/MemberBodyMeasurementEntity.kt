package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import com.mall.choisinsa.common.enumeration.MemberBodyMeasurementType
import com.mall.choisinsa.member.model.MemberBodyMeasurement
import jakarta.persistence.*

@Entity
@Table(name = "member_body_measurement")
class MemberBodyMeasurementEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    var memberId: Long,

    @Enumerated(EnumType.STRING)
    var type: MemberBodyMeasurementType,

    var value: String,
) : BaseDateTimeEntity() {

    companion object {
        fun from(
            memberBodyMeasurement: MemberBodyMeasurement
        ): MemberBodyMeasurementEntity {
            return MemberBodyMeasurementEntity(
                id = memberBodyMeasurement.id,
                memberId = memberBodyMeasurement.memberId,
                type = memberBodyMeasurement.type,
                value = memberBodyMeasurement.value,
            )
        }
    }

    fun toModel(): MemberBodyMeasurement {
        return MemberBodyMeasurement(
            id = this.id,
            memberId = this.memberId,
            type = this.type,
            value = this.value,
            createdDt = super.createdDt,
        )
    }
}