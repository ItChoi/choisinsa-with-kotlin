package com.mall.choisinsa.common.http

import com.mall.choisinsa.common.enumeration.AuthorizationType
import org.springframework.http.MediaType

interface HttpCommunication {

    fun requestGet(
        url: String,
        data: Any?
    ): Any?

    fun requestPost(
        url: String,
        mediaType: MediaType,
        requestDtos: Any?
    ): Any?

    fun requestPostWithToken(
        url: String,
        mediaType: MediaType,
        type: AuthorizationType,
        token: String?
    ): Any?
}