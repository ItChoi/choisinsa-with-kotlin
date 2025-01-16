package com.mall.choisinsa.common.enumeration.exception

enum class ExceptionType(
    val msg: String,
    val clientErrorMsg: String? = null,
) {
    SERVER_ERROR("서버 에러"),
    BAD_REQUEST("잘못된 요청"),
    MISMATCH_REQUEST("입력하신 정보가 일치하지 않습니다."),
}