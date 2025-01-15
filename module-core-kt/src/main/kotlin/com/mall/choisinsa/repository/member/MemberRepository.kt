package com.mall.choisinsa.repository.member

import com.mall.choisinsa.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {

}