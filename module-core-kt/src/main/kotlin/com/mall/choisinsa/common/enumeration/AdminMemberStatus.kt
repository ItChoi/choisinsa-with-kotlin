package com.mall.choisinsa.common.enumeration

enum class AdminMemberStatus(
    private val desc: String
) {
    NORMAL("정상"),
    LEAVE("회원 탈퇴"),
    RESIGNED("퇴사"),
}