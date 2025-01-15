package com.mall.choisinsa.common.util

import io.micrometer.common.util.StringUtils

class Validation {
    companion object {

        fun areAllNotBlank(vararg values: String?): Boolean {
            require(values.isNotEmpty()) {
                return false
            }

            return values.all { StringUtils.isNotBlank(it) }
        }
    }
}