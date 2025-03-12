package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.controller.response.MemberResponse
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.mall.choisinsa.member.domain.QMemberEntity.memberEntity as member
import com.mall.choisinsa.member.domain.QMemberAddressEntity.memberAddressEntity as memberAddress


@Repository
class MemberQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun count(request: MemberRequest): Int {
        return queryFactory
            .select(member.id)
            .from(member)
            .where(
                request.loginId?.let { member.loginId.eq(request.loginId) },
                request.email?.let { member.loginId.eq(request.loginId) },
                request.loginId?.let { member.loginId.eq(request.loginId) }
            ).fetch()?.size ?: 0
    }

    fun findMemberResponseById(memberId: Long): MemberResponse? {
        return queryFactory
            .select(
                Projections.fields(
                    MemberResponse::class.java,
                    member.loginId,
                    member.name,
                    member.nickName,
                    member.email,
                    member.phoneNumber,
                    member.birthday,
                    member.gender,
                )
            )
            .from(member)
            .leftJoin(memberAddress)
            .on(memberAddress.memberId.eq(member.id))
            .where(
                member.id.eq(memberId)
            )
            .fetchFirst()
    }

}