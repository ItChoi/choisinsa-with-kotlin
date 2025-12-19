package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.member.model.Member
import com.mall.choisinsa.member.domain.MemberEntity
import com.mall.choisinsa.member.service.port.CoreMemberRepository
import org.springframework.stereotype.Repository

@Repository
class CoreMemberRepositoryImpl(
    private val coreMemberJpaRepository: CoreMemberJpaRepository
) : CoreMemberRepository {
    override fun findByLoginIdAndStatus(
        loginId: String,
        status: MemberStatus
    ): Member? {
        return coreMemberJpaRepository.findByLoginIdAndStatus(loginId, status)?.toModel()
    }

    override fun save(member: Member): Member {
        return coreMemberJpaRepository.save(MemberEntity.from(member)).toModel()
    }
}