package com.mall.choisinsa.oauth2.controller.response

import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.common.enumeration.SnsType

class Oauth2UserResponseDto(
    // OAuth 고유 id
    val id: String? = null,
    val nickname: String? = null,
    val profileImageUrl: String? = null,
    // 이용자 이름
    val name: String? = null,
    val email: String? = null,
    val gender: GenderType? = null,
    val birthyear: String? = null,
    val birthday: String? = null,
    val phoneNumber: String? = null,
    val snsType: SnsType,
) {
}