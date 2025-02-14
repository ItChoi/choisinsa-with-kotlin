package com.mall.choisinsa.common.exception

import com.mall.choisinsa.common.enumeration.exception.ExceptionType

class GlobalException(
    val exceptionType: ExceptionType,
) : RuntimeException()