package com.mall.choisinsa.service

import com.mall.choisinsa.dto.MemberDto
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MemberService(

) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        return MemberDto("asd")
    }
}