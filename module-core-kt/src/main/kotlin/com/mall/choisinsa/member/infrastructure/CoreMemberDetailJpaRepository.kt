package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.domain.MemberDetailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberDetailJpaRepository : JpaRepository<MemberDetailEntity, Long> {

}