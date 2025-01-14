package com.mall.choisinsa.common.enumeration

enum class MemberStatus(
    private val desc: String
) {
    NORMAL("정상"),
    LEAVE("회원 탈퇴"),
    BAN("회원 영구 정지"),
    STOP("일시 정지"),
}