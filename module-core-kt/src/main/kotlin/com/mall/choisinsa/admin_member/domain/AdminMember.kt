package com.mall.choisinsa.admin_member.domain

import com.mall.choisinsa.common.enumeration.AdminMemberStatus
import com.mall.choisinsa.common.enumeration.AdminType

class AdminMember(
    private val id: Long? = null,
    private val adminType: AdminType,
    private val loginId: String,
    private val password: String,
    private val status: AdminMemberStatus,
    private val name: String? = null,
    private val email: String? = null,
    private val nickName: String? = null,
    private val phoneNumber: String? = null,
) {
}