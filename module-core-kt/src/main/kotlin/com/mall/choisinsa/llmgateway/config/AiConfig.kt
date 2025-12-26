package com.mall.choisinsa.llmgateway.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// ChatClient Bean -> Spring AI는 ChatClient 플루언트 API를 제공
@Configuration
class AiConfig {

    // ChatClient.Builder는 Spring AI 오토컨피그가 주입해줌
    // Service에서는 chatClient.prompt()로 호출
    @Bean
    fun chatClient(
        builder: ChatClient.Builder
    ): ChatClient = builder.build()

}