package com.mall.choisinsa.common.enumeration

enum class MemberCertificationStatus(
    private val desc: String,
) {
    SUCCESS("인증 성공"),
    FAILURE("인증 실패"),
    TIMEOUT("시간 초과"),
    END("종료(인증 진행 X)"),
}