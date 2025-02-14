package com.mall.choisinsa.common.crypto

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.security.SecureRandom
import java.util.*


class AesGcmCryptoTest{
    val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule()

    @Test
    @DisplayName("random_base64_encoding")
    fun random_base64_encoding() {
        val keyBytes = ByteArray(32) // 32 bytes = 256 bits
        SecureRandom().nextBytes(keyBytes) // 안전한 랜덤 바이트 생성
        val base64 = Base64.getEncoder().encodeToString(keyBytes) // Base64 인코딩
        println("base64: " + base64)
    }

    @Test
    @DisplayName("개인정보 양방향 암호화, 평문을 암호화하고 복호화 할 수 있어야 한다.")
    fun 개인정보_양방향_암호화() {
        // given
        val aesGcmCrypto = AesGcmCrypto(objectMapper, "tf0La37EPD8/HG1yxY2mV3SEJN8qTm7SwRmBM0+yzMs=")
        var email = "test@test.com"

        // when
        val encrypt = aesGcmCrypto.encrypt(email)
        val encryptTemp = aesGcmCrypto.encrypt(email)
        val decrypt = aesGcmCrypto.decrypt(encrypt)

        // then
        assertThat(encrypt).isNotEqualTo(encryptTemp)
        assertThat(email).isEqualTo(decrypt)
    }
}