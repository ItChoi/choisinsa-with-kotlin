package com.mall.choisinsa.common.enumeration.exception

enum class ExceptionType(
    val msg: String,
    val clientErrorMsg: String? = null,
) {
    SERVER_ERROR("서버 에러"),
    BAD_REQUEST("잘못된 요청"),
    REQUIRED_ITEM_NOT_PROVIDED("필수 항목을 입력하지 않았습니다."),
    INVALID_REQUEST("유효하지 않은 요청 정보입니다."),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),
    FIELD_TOO_LONG("입력 값이 너무 깁니다."),
    FIELD_TOO_SHORT("입력 값이 너무 짧습니다."),
    MISMATCH_REQUEST("입력하신 정보가 일치하지 않습니다."),
    ALREADY_EXISTS_MEMBER("이미 존재하는 회원입니다."),
    NOT_FOUND_MEMBER("회원을 찾을 수 없습니다."),
    WRONG_JWT_TOKEN("잘못된 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN("만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN("지원하지 않는 JWT 토큰입니다."),
    INVALID_JWT_TOKEN("유효하지 않은 JWT 토큰입니다."),
    CAN_NOT_REISSUE_TOKEN("액세스 토큰을 재발급 받을 수 없습니다."),
    CAN_NOT_JSON_CONVERT("json convert가 되지 않았습니다. 데이터를 확인해주세요."),
}