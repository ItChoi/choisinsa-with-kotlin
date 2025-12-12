package com.mall.choisinsa.member.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "member_privacy_search")
@Entity
class MemberPrivacySearchEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val memberId: Long,
    private val name: String?,
    private val email: String?,
    private val birthday: String?,
    private val phoneNumber: String?,
    private val nameEncrypted: String?,
    private val emailEncrypted: String?,
    private val phoneNumberEncrypted: String?,
    private val birthdayEncrypted: String?,
) : BaseDateTimeEntity()