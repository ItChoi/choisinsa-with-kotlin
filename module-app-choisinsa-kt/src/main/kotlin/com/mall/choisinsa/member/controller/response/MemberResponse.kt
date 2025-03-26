package com.mall.choisinsa.member.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.mall.choisinsa.common.enumeration.GenderType
import java.time.LocalDateTime

data class MemberResponse(
    var loginId: String,
    var name: String?,
    var nickName: String?,
    var email: String?,
    var phoneNumber: String?,
    var birthday: String?,
    var gender: GenderType,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var createdDt: LocalDateTime?,
) {
    constructor() : this("", "", "", "", "", "", GenderType.NONE, null)
}
