package com.mall.choisinsa.member.service.port

import com.mall.choisinsa.member.domain.MemberPrivacySearchEntity

interface MemberPrivacySearchRepository {
    fun save(from: MemberPrivacySearchEntity)
}