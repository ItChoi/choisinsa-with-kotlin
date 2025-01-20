package com.mall.choisinsa.web.aop

import com.mall.choisinsa.dto.global.ResponseWrapper
import com.mall.choisinsa.web.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GlobalException::class)
    fun errorTypeExHandler(e: GlobalException): ResponseWrapper {
        return ResponseWrapper.error(
            HttpStatus.BAD_REQUEST,
            e.exceptionType,
        )
    }
}