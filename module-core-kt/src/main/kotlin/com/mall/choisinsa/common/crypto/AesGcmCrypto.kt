package com.mall.choisinsa.common.crypto

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AesGcmCrypto(
    private val objectMapper: ObjectMapper = ObjectMapper().registerKotlinModule(),

    @Value("\${choisinsa-client-app.crypto.secret-key}")
    private val base64Key: String
) {
    companion object {
        // TODO: yml로 감추기
        private const val AES_ALGORITHM = "AES"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val GCM_TAG_LENGTH = 128 // bits
        private const val IV_SIZE = 12         // 12 bytes for GCM recommended
    }

    private val secretKey: ByteArray by lazy {
        // Base64 decode -> 32 bytes -> AES-256
        Base64.getDecoder().decode(base64Key).also { keyBytes ->
            require(keyBytes.size == 32) { "AES-256 key must be 32 bytes" }
        }
    }

    fun encrypt(
        value: String,
    ): String {
        return encryptToBase64(
            objectMapper.writeValueAsString(encrypt(value.toByteArray()))
        )
    }

    fun decrypt(
        value: String,
    ): String {
        return decrypt(
            objectMapper.readValue(decryptFromBase64(value), EncryptedResult::class.java)
        ).decodeToString()
    }

    /**
     * 암호화
     * @param plainBytes 평문 바이트
     * @return EncryptedResult( cipherText, iv )
     */
    private fun encrypt(plainBytes: ByteArray): EncryptedResult {
        val secureRandom = SecureRandom()
        val iv = ByteArray(IV_SIZE).apply {
            secureRandom.nextBytes(this)
        }

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(secretKey, AES_ALGORITHM)
        val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec)
        val cipherText = cipher.doFinal(plainBytes)

        return EncryptedResult(cipherText, iv)
    }

    /**
     * 복호화
     */
    private fun decrypt(encryptedResult: EncryptedResult): ByteArray {
        val (cipherText, iv) = encryptedResult

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(secretKey, AES_ALGORITHM)
        val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec)

        return cipher.doFinal(cipherText)
    }

    // Base64 암호화
    private fun encryptToBase64(plainText: String): String {
        val encodedBytes = Base64.getEncoder().encode(plainText.toByteArray())
        return String(encodedBytes) // 다시 문자열로 변환하여 반환
    }

    // Base64 복호화
    private fun decryptFromBase64(encryptedText: String): String {
        val decodedBytes = Base64.getDecoder().decode(encryptedText)
        return String(decodedBytes)
    }

}

data class EncryptedResult(
    val cipherText: ByteArray,
    val iv: ByteArray
)
