package com.mall.choisinsa.repository.member

import com.mall.choisinsa.domain.member.MemberDetail
import org.springframework.data.jpa.repository.JpaRepository

interface MemberDetailRepository : JpaRepository<MemberDetail, Long> {

}