package com.mall.choisinsa.mock

import com.mall.choisinsa.member.service.port.MailSender

class FakeMailSender(
) : MailSender {
    public var email: String? = null
    public var title: String? = null
    public var content: String? = null
    override fun send(email: String, title: String, content: String) {
        this.email = email
        this.title = title
        this.content = content
    }
}