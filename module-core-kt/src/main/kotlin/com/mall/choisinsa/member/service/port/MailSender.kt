package com.mall.choisinsa.member.service.port

interface MailSender {
    fun send(
        email: String,
        title: String,
        content: String
    )
}