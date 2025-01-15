package com.mall.choisinsa.common.enumeration

enum class AdminMemberStatus(
    private val desc: String
) {
    NORMAL("정상"),
    PENDING_APPROVAL("승인 대기, 승인시 NORMAL"),
    RESIGNED("퇴사"),
}