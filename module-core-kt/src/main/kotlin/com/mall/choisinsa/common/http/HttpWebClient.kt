package com.mall.choisinsa.common.http

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.enumeration.AuthorizationType
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.exception.GlobalException
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.util.StringUtils
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode
import org.springframework.web.util.UriBuilder
import java.net.URI
import java.util.function.Function

@Component
class HttpWebClient(
    val AUTHORIZATION_BEARER: String = "Bearer ",
) : HttpCommunication {

    override fun requestGet(
        url: String,
        data: Any?
    ): Any? {
        return WebClient.builder()
            .baseUrl(url)
            .uriBuilderFactory(getDefaultUriBuilderFactory(url!!, EncodingMode.VALUES_ONLY))
            .build()
            .get()
            .uri(getUriBuilderInGetForContentType(data))
            .retrieve()
            .bodyToMono(Any::class.java)
            .block()
    }

    override fun requestPost(
        url: String,
        mediaType: MediaType,
        datas: Any?
    ): Any? {

        return initWebClientForPostRestApi(url!!, mediaType)
            .post()
            .bodyValue(getBodyInPostForContentType(mediaType, datas))
            .retrieve()
            .bodyToMono(Any::class.java)
            .block()
    }

    override fun requestPostWithToken(
        url: String,
        mediaType: MediaType,
        type: AuthorizationType,
        token: String?
    ): Any? {

        return initWebClientForPostRestApi(url!!, mediaType)
            .post()
            .header(type.type, getFormattingTokenWithAuthType(type, token))
            .retrieve()
            .bodyToMono<Any?>(Any::class.java)
            .block()
    }

    private fun getFormattingTokenWithAuthType(
        type: AuthorizationType?,
        token: String?
    ): String? {
        if (type === AuthorizationType.AUTHORIZATION) {
            return AUTHORIZATION_BEARER + token
        }

        return token
    }

    private fun initWebClientForPostRestApiWithToken(
        url: String,
        mediaType: MediaType?,
        type: AuthorizationType?,
        token: String?
    ): WebClient {
        if (type == null || !StringUtils.hasText(token)) {
            return initWebClientForPostRestApi(url, mediaType)
        }

        if (mediaType == null) {
            return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(type.type, token)
                .build()
        }

        return WebClient.builder()
            .baseUrl(url)
            .defaultHeader(type.type, token)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, getMediaTypeValue(mediaType))
            .build()
    }

    private fun initWebClientForPostRestApi(
        url: String,
        mediaType: MediaType?
    ): WebClient {
        if (mediaType == null) {
            return WebClient.builder()
                .baseUrl(url)
                .build()
        }

        return WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, getMediaTypeValue(mediaType))
            .build()
    }

    private fun getMediaTypeValue(mediaType: MediaType): String {
        return mediaType.getType() + "/" + mediaType.getSubtype()
    }

    private fun getBodyInPostForContentType(
        mediaType: MediaType?,
        requestDtos: Any?
    ): BodyInserter<*, *> {
        val objectMapper = ObjectMapper()

        try {
            val jsonString = objectMapper.writeValueAsString(requestDtos)
            val jsonNode = objectMapper.readTree(jsonString)


            if (MediaType.APPLICATION_JSON === mediaType || MediaType.APPLICATION_JSON_UTF8 === mediaType) {
                return BodyInserters.fromValue<String?>(jsonString)
            }

            if (MediaType.MULTIPART_FORM_DATA === mediaType) {
            }

            if (MediaType.APPLICATION_FORM_URLENCODED === mediaType) {
                val formData: MultiValueMap<String?, String?> = LinkedMultiValueMap<String?, String?>()

                val fields = jsonNode.fields()
                while (fields.hasNext()) {
                    val next = fields.next()
                    formData.add(next.key, next.value!!.asText())
                }
                return BodyInserters.fromFormData(formData)
            }
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

        throw GlobalException(ExceptionType.BAD_REQUEST)
    }

    private fun getUriBuilderInGetForContentType(
        requestDtos: Any?
    ): String {
        try {
            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(requestDtos)
            val jsonNode = objectMapper.readTree(jsonString)

            val fields = jsonNode.fields()
            val uriBuilderFunction: Function<UriBuilder?, URI?>?
            uriBuilderFunction = Function { uriBuilder: UriBuilder? ->
                while (fields.hasNext()) {
                    val next = fields.next()
                    uriBuilder!!.queryParam(next.key, next.value!!.asText())
                }
                val build = uriBuilder!!.build()
                build
            }

            return uriBuilderFunction.toString()
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    private fun getDefaultUriBuilderFactory(url: String, encodingMode: EncodingMode): DefaultUriBuilderFactory {
        val factory = DefaultUriBuilderFactory(url)
        factory.setEncodingMode(encodingMode)
        return factory
    }

}