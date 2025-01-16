package com.mall.choisinsa.dto.global

import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import org.springframework.http.HttpStatus

class ResponseWrapper private constructor (
    private val status: HttpStatus,
    private val exceptionType: ExceptionType?,
    private val exceptionMsg: String?,
    private val data: Any?,
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