package com.mall.choisinsa.oauth2.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.mall.choisinsa.common.enumeration.AuthorizationType
import com.mall.choisinsa.common.enumeration.GenderType
import com.mall.choisinsa.common.enumeration.SnsType
import com.mall.choisinsa.common.enumeration.exception.ExceptionType
import com.mall.choisinsa.common.exception.GlobalException
import com.mall.choisinsa.common.http.HttpCommunication
import com.mall.choisinsa.member.service.CoreMemberService
import com.mall.choisinsa.member.service.MemberService
import com.mall.choisinsa.oauth2.controller.request.KakaoOauthTokenDto
import com.mall.choisinsa.oauth2.controller.request.KakaoOauthTokenRequestDto
import com.mall.choisinsa.oauth2.controller.request.Oauth2LoginRequestDto
import com.mall.choisinsa.oauth2.controller.response.Oauth2LoginResponseDto
import com.mall.choisinsa.oauth2.controller.response.Oauth2ResponseDto
import com.mall.choisinsa.oauth2.controller.response.Oauth2UserResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils

@Service
class Oauth2Service(
    @Value("\${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private val authorizationGrantType: String,

    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}")
    private val clientId: String,

    @Value("\${spring.security.oauth2.client.registration.kakao.client-secret}")
    private val clientSecret: String,

    @Value("\${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private val redirectUri: String,

    @Value("\${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private val authorizationUri: String,

    @Value("\${spring.security.oauth2.client.provider.kakao.token-uri}")
    private val tokenUri: String,

    @Value("\${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private val userInfoUri: String,

    private val httpCommunication: HttpCommunication,

    private val objectMapper: ObjectMapper,

    private val coreMemberService: CoreMemberService,
) {
    fun login(
        snsType: SnsType,
        request: Oauth2LoginRequestDto
    ): Oauth2LoginResponseDto {
        return when (snsType) {
            SnsType.KAKAO -> loginWithKakao(request)
            else -> throw GlobalException(ExceptionType.BAD_REQUEST)
        }
    }

    fun loginWithKakao(
        request: Oauth2LoginRequestDto
    ): Oauth2LoginResponseDto {
        val oauthCode = authorizeOauth(request)

        val kakaoOauthTokenDto = objectMapper.convertValue(objectMapper, KakaoOauthTokenDto::class.java)
        val obj = getUserWithKakao(kakaoOauthTokenDto.accessToken)

        val snsUserInfo: Oauth2UserResponseDto = getUserWithKakaoInfo(obj)

        Oauth2ResponseDto(
            coreMemberService.existsBySnsIdAndSnsType(snsUserInfo.id, snsUserInfo.snsType),
            coreMemberService.isExistEmail(snsUserInfo.email),
            snsUserInfo
        )
        
        return Oauth2LoginResponseDto() 
    }

    private fun getUserWithKakao(accessToken: String): Any? = httpCommunication.requestPostWithToken(
        userInfoUri,
        MediaType.APPLICATION_FORM_URLENCODED,
        AuthorizationType.AUTHORIZATION,
        accessToken
    )

    private fun authorizeOauth(request: Oauth2LoginRequestDto): Any? = httpCommunication.requestPost(
        tokenUri,
        MediaType.APPLICATION_FORM_URLENCODED,
        KakaoOauthTokenRequestDto(
            authorizationGrantType,
            clientId,
            redirectUri,
            request.code,
            clientSecret,
        )
    )

    private fun getUserWithKakaoInfo(
        obj: Any?
    ): Oauth2UserResponseDto {
        try {
            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(obj)
            val getKakaoUserWithProperties: MutableMap<String?, Any?> =
                objectMapper.readValue(jsonString, HashMap::class.java) as MutableMap<String?, Any?>

            val kakaoAccount = getKakaoUserWithProperties.get("kakao_account") as MutableMap<String?, Any?>
            val id = ObjectUtils.nullSafeToString(getKakaoUserWithProperties.get("id"))
            val nickname =
                ObjectUtils.nullSafeToString((kakaoAccount.get("profile") as MutableMap<String?, Any?>).get("nickname"))
            val profile_image_url =
                ObjectUtils.nullSafeToString((kakaoAccount.get("profile") as MutableMap<String?, Any?>).get("profile_image_url"))
            val email = ObjectUtils.nullSafeToString(kakaoAccount.get("email"))
            val gender = ObjectUtils.nullSafeToString(kakaoAccount.get("gender"))

            return Oauth2UserResponseDto(id = id,
                nickname = nickname,
                profileImageUrl = profile_image_url,
                email = email,
                gender = GenderType.from(gender),
                snsType = SnsType.KAKAO)
        } catch (e: JsonProcessingException) {
            throw GlobalException(ExceptionType.CAN_NOT_JSON_CONVERT)
        }
    }

}