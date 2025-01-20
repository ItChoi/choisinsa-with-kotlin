package com.mall.choisinsa.dto.global

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import org.springframework.http.HttpStatus

class ResponseWrapper private constructor (
    val status: HttpStatus,
    val exceptionType: ExceptionType?,
    val exceptionMsg: String?,
    val data: Any?,
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

        fun error(
            status: HttpStatus,
            exceptionType: ExceptionType,
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