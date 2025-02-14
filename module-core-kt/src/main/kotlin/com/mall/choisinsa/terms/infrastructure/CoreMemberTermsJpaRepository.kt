package com.mall.choisinsa.terms.infrastructure

import com.mall.choisinsa.terms.domain.MemberTermsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreMemberTermsJpaRepository : JpaRepository<MemberTermsEntity, Long> {

}