package com.mall.choisinsa.web.exception

import com.mall.choisinsa.common.enumeration.exception.ExceptionType

class GlobalException(
    val exceptionType: ExceptionType,
) : RuntimeException() {


}