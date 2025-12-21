package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.model.MemberBodyMeasurement

interface MemberBodyMeasurementRepository {
    fun findAllMemberBodyMeasurementResponseBy(memberId: Long): List<MemberBodyMeasurement>
}