package com.mall.choisinsa.dto

import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDto(
    val _username: String,
    var _password: String? = null,
    val _authorities: MutableList<GrantedAuthority> = mutableListOf()
) : UserDetails, CredentialsContainer {
    override fun getUsername(): String {
        return this._username
    }

    override fun getPassword(): String? {
        return this._password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authorities
    }

    override fun eraseCredentials() {
        this._password = null
    }
}
