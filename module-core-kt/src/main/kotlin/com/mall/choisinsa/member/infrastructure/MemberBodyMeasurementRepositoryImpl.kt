package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.model.MemberBodyMeasurement
import com.mall.choisinsa.member.service.port.MemberBodyMeasurementRepository
import org.springframework.stereotype.Repository

@Repository
class MemberBodyMeasurementRepositoryImpl(
    private val memberSizeJpaRepository: MemberSizeJpaRepository,
) : MemberBodyMeasurementRepository {

    override fun findAllMemberBodyMeasurementResponseBy(memberId: Long): List<MemberBodyMeasurement> {
        return memberSizeJpaRepository.findAllByMemberId(memberId)
            .map { it.toModel() }
    }
}