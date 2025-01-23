package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.dto.request.MemberRequestDto
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.mall.choisinsa.member.domain.QMemberEntity.memberEntity as member


@Repository
class MemberQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun count(request: MemberRequestDto): Int {
        return queryFactory
            .select(member.id)
            .from(member)
            .where(
                request.loginId?.let { member.loginId.eq(request.loginId) },
                request.email?.let { member.loginId.eq(request.loginId) },
                request.loginId?.let { member.loginId.eq(request.loginId) }
            ).fetch()?.size ?: 0
    }

}