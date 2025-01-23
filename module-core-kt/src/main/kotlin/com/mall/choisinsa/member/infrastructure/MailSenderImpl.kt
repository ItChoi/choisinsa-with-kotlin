package com.mall.choisinsa.member.infrastructure

import com.mall.choisinsa.member.service.port.MailSender
import org.springframework.stereotype.Component

@Component
class MailSenderImpl(
    // private val javaMailSender: JavaMailSender,
) : MailSender {
    override fun send(email: String, title: String, content: String) {
        // TODO
    }
}