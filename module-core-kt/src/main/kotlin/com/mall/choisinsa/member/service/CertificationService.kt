package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.service.port.MailSender
import org.springframework.stereotype.Service

@Service
class CertificationService(
    private  val mailSender: MailSender,
) {

    fun send(
        email: String,
        memberId: Long,
        certificationCode: String,
    ) {
        val certificationUrl = generateCertificationUrl(memberId, certificationCode)
        val title = "Please certify your email address"
        val content = "Please click the following link to certify your email address: ${certificationUrl}"

        mailSender.send(email, title, content)
    }

    fun generateCertificationUrl(
        memberId: Long,
        certificationCode: String,
    ): String {
        // TEMP CODE
        return "http://localhost:8080/api/users/${memberId}/verify?certificationCode=${certificationCode}"
    }
}