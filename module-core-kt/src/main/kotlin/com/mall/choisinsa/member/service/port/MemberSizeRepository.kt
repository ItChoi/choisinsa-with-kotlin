package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.domain.MemberSize

interface MemberSizeRepository {
    fun findAllMemberSizeResponseBy(memberId: Long): List<MemberSize>

}