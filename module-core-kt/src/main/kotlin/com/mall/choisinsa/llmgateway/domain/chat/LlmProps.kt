package com.mall.choisinsa.llmgateway.domain.chat

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class LlmProps(
    @Value("\${llm.session.max-messages}") val maxMessages: Int
)