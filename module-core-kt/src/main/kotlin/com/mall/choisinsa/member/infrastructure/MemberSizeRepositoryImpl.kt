package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberSize
import com.mall.choisinsa.member.service.port.MemberSizeRepository
import org.springframework.stereotype.Repository

@Repository
class MemberSizeRepositoryImpl(
    private val memberSizeJpaRepository: MemberSizeJpaRepository,
) : MemberSizeRepository {

    override fun findAllMemberSizeResponseBy(memberId: Long): List<MemberSize> {
        return memberSizeJpaRepository.findAllByMemberId(memberId)
            .map { it.toModel() }
    }
}