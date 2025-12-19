package com.mall.choisinsa.member.model

import com.mall.choisinsa.member.domain.dto.request.MemberPrivacySearchRequest
import com.mall.choisinsa.member.domain.dto.request.MemberRequest
import com.mall.choisinsa.member.service.port.MemberPrivacySearchRepository
import java.time.LocalDateTime


class MemberPrivacySearch(
    var id: Long? = null,
    var memberId: Long,
    var name: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    var phoneNumber: String? = null,
    var nameEncrypted: String,
    var emailEncrypted: String,
    var phoneNumberEncrypted: String,
    var birthdayEncrypted: String,
    var createdDt: LocalDateTime? = null,
    var updatedDt: LocalDateTime? = null,
) {
    companion object {
        fun of(
            memberId: Long,
            request: MemberRequest,
            memberPrivacySearchRequest: MemberPrivacySearchRequest,
        ): MemberPrivacySearch {
            return MemberPrivacySearch(
                memberId = memberId,
                name = request.name,
                email = request.email,
                birthday = request.birthday,
                phoneNumber = request.phoneNumber,
                nameEncrypted = memberPrivacySearchRequest.nameEncrypted,
                emailEncrypted = memberPrivacySearchRequest.emailEncrypted,
                phoneNumberEncrypted = memberPrivacySearchRequest.phoneNumberEncrypted,
                birthdayEncrypted = memberPrivacySearchRequest.birthdayEncrypted,
            )
        }
    }
}