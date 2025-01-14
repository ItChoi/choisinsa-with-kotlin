package com.mall.choisinsa.dto.wrapper

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import org.springframework.http.HttpStatus

class ResponseWrapper(
    private val status: HttpStatus,
    private val exceptionType: ExceptionType? = null,
    private val exceptionMsg: String? = null,
    private val data: Any? = null,
) {
    companion object {
        fun ok(
            status: HttpStatus = HttpStatus.OK,
            exceptionType: ExceptionType? = null,
            exceptionMsg: String? = null,
            data: Any? = null,
        ): ResponseWrapper {
            return ResponseWrapper(
                status,
                exceptionType,
                exceptionMsg,
                data,
            )
        }
    }
}