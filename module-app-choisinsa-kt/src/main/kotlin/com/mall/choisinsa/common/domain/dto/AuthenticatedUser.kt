package com.mall.choisinsa.common.domain.dto

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.common.enumeration.MemberType
import com.mall.choisinsa.member.model.Member
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthenticatedUser private constructor (
    val id: Long,
    val status: MemberStatus,
    val nickName: String? = null,
    private val _username: String,
    private var _password: String? = null,
    private val _authorities: MutableList<GrantedAuthority> = mutableListOf(),
) : UserDetails, CredentialsContainer {

    companion object {
        fun of(member: Member): AuthenticatedUser {
            return AuthenticatedUser(
                id = member.id!!,
                status = member.status,
                nickName = member.nickName,
                _username = member.loginId,
                _password = member.password,
                _authorities = mutableListOf(
                    SimpleGrantedAuthority(MemberType.MEMBER.toString())
                )
            )
        }
    }

    override fun getUsername(): String {
        return this._username
    }

    override fun getPassword(): String? {
        return this._password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this._authorities
    }


    override fun eraseCredentials() {
        this._password = null
    }

    fun toUsernamePasswordAuthenticationToken(): UsernamePasswordAuthenticationToken {
        eraseCredentials()
        return UsernamePasswordAuthenticationToken(
            this,
            null,
            this.authorities
        )
    }

    fun toTokenPayload(): MutableMap<String, Any?> {
        return mutableMapOf(
            "id" to this.id,
            "status" to this.status,
            "nickName" to this.nickName,
            "loginId" to this._username,
            "authorities" to this._authorities,
        )
    }
}
