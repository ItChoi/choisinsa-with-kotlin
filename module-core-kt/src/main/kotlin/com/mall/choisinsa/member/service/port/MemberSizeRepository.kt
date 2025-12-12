package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.domain.MemberBodyMeasurement

interface MemberSizeRepository {
    fun findAllMemberSizeResponseBy(memberId: Long): List<MemberBodyMeasurement>

}