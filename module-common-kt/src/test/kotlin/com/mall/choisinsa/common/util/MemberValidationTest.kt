package com.mall.choisinsa.common.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MemberValidationTest {
    @Test
    @DisplayName("")
    fun test() {
        val phone = "010-0000-0000"
        val test = MemberValidation.isValidPhoneNumber(phone)
        print("test: $test")


    }
}