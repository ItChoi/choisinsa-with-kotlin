package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberDetail
import com.mall.choisinsa.member.domain.MemberDetailEntity
import com.mall.choisinsa.member.service.port.CoreMemberDetailRepository
import org.springframework.stereotype.Repository

@Repository
class CoreMemberDetailRepositoryImpl(
    private val coreMemberDetailJpaRepository : CoreMemberDetailJpaRepository,
) : CoreMemberDetailRepository {

    override fun save(
        memberDetail: MemberDetail
    ) {
        coreMemberDetailJpaRepository.save(MemberDetailEntity.from(memberDetail))
    }
}