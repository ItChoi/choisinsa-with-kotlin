package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.model.MemberSnsConnect
import com.mall.choisinsa.member.service.port.CoreMemberSnsConnectRepository
import org.springframework.stereotype.Repository

@Repository
class CoreMemberSnsConnectRepositoryImpl(
    private val coreMemberSnsConnectJpaRepository: CoreMemberSnsConnectJpaRepository,
) : CoreMemberSnsConnectRepository {

    override fun findAllByMemberId(
        memberId: Long
    ): List<MemberSnsConnect> {
        return coreMemberSnsConnectJpaRepository.findAllByMemberId(memberId)
            .map { it.toModel() }
    }
}