package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.dto.response.MemberBodyMeasurementResponse
import com.mall.choisinsa.member.service.port.MemberBodyMeasurementRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberBodyMeasurementService(
    private val memberBodyMeasurementRepository: MemberBodyMeasurementRepository,
) {
    @Transactional(readOnly = true)
    fun findAllMemberBodyMeasurementResponseBy(
        memberId: Long
    ): List<MemberBodyMeasurementResponse> {
        return memberBodyMeasurementRepository.findAllMemberBodyMeasurementResponseBy(memberId)
            .map { MemberBodyMeasurementResponse.from(it) }

    }

}