package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.common.enumeration.MemberAddressStatus
import com.mall.choisinsa.member.domain.dto.response.MemberResponse
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.mall.choisinsa.member.domain.QMemberEntity.memberEntity as member
import com.mall.choisinsa.member.domain.QMemberAddressEntity.memberAddressEntity as memberAddress
import com.mall.choisinsa.member.domain.QMemberPrivacySearchEntity.memberPrivacySearchEntity as memberPrivacySearch


@Repository
class MemberQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun count(request: MemberRequest): Int {
        return queryFactory
            .select(member.id)
            .from(member)
            .where(
                request.loginId?.let { member.loginId.eq(request.loginId) }
            ).fetch()?.size ?: 0
    }

    fun findMemberResponseById(memberId: Long): MemberResponse? {
        return queryFactory
            .select(
                Projections.fields(
                    MemberResponse::class.java,
                    member.loginId,
                    memberPrivacySearch.name,
                    member.nickName,
                    memberPrivacySearch.email,
                    memberPrivacySearch.phoneNumber,
                    memberPrivacySearch.birthday,
                    member.gender,
                    member.createdDt,
                )
            )
            .from(member)
            .innerJoin(memberPrivacySearch)
            .on(memberPrivacySearch.memberId.eq(member.id))
            .leftJoin(memberAddress)
            .on(memberAddress.memberId.eq(member.id)
                .and(memberAddress.status.eq(MemberAddressStatus.MAIN)))
            .where(
                member.id.eq(memberId)
            )
            .fetchFirst()
    }

}