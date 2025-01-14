package com.mall.choisinsa.common.enumeration.exception

enum class ExceptionType(
    private val msg: String,
    private val clientErrorMsg: String? = null,
) {
    SERVER_ERROR("서버 에러"),
    BAD_REQUEST("잘못된 요청"),
}