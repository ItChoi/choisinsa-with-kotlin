package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberPrivacySearchEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberPrivacySearchJpaRepository : JpaRepository<MemberPrivacySearchEntity, Long> {
    fun existsByEmail(email: String): Boolean
}