package com.mall.choisinsa

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.security.SecureRandom
import java.util.*

class ChoisinsaApplicationTests {

    @Test
    @DisplayName("Base64 encoding 값")
    fun test() {
        val keyBytes = ByteArray(32) // 32 bytes = 256 bits
        SecureRandom().nextBytes(keyBytes) // 안전한 랜덤 바이트 생성
        val base64 = Base64.getEncoder().encodeToString(keyBytes) // Base64 인코딩
        println("base64: " + base64)
    }
}

