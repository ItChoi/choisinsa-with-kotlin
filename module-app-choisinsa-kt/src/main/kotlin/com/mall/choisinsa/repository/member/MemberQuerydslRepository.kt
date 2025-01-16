package com.mall.choisinsa.repository.member

import com.mall.choisinsa.domain.member.QMember.member
import com.mall.choisinsa.dto.request.member.MemberRequestDto
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository


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