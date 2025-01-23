package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.domain.MemberDetail

interface CoreMemberDetailRepository {
    fun save(memberDetail: MemberDetail)

}