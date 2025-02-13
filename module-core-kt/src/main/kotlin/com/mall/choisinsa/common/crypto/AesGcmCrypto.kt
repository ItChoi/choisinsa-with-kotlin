package com.mall.choisinsa.common.crypto

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

@Component
class AesGcmCrypto(
    @Value("\${choisinsa-client-app.crypto.secret-key}")
    private val base64Key: String
) {
    companion object {
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

    /**
     * 암호화
     * @param plainBytes 평문 바이트
     * @return EncryptedResult( cipherText, iv )
     */
    fun encrypt(plainBytes: ByteArray): EncryptedResult {
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

    fun encrypt(
        value: String,
    ): String {
        val result = encrypt(value.toByteArray())
        return result.cipherText.toString()
    }

    /**
     * 복호화
     */
    fun decrypt(encryptedResult: EncryptedResult): ByteArray {
        val (cipherText, iv) = encryptedResult

        val cipher = Cipher.getInstance(TRANSFORMATION)
        val keySpec = SecretKeySpec(secretKey, AES_ALGORITHM)
        val gcmSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec)

        return cipher.doFinal(cipherText)
    }

//    fun decrypt(
//        value: String,
//    ): String {
//        decrypt()
//    }

}

data class EncryptedResult(
    val cipherText: ByteArray,
    val iv: ByteArray
)
