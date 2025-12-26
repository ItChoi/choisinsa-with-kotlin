package com.mall.choisinsa.llmgateway.api

import com.mall.choisinsa.common.dto.ChatRequest
import com.mall.choisinsa.llmgateway.domain.chat.dto.ChatResponse
import com.mall.choisinsa.llmgateway.service.ChatService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/chat")
@RestController
class ChatController(
    private val chatService: ChatService,
) {

    @PostMapping
    fun chat(
        @Validated @RequestBody request: ChatRequest
    ): ChatResponse {
        return chatService.chat(request)
    }

}