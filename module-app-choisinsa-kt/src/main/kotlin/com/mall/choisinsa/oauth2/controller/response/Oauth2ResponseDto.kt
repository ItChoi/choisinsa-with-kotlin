package com.mall.choisinsa.oauth2.controller.response

class Oauth2ResponseDto(
    var isAlreadyConnectSns: Boolean, //
    var isAlreadyExistEmail: Boolean, // 이메일만 존재, 연동 여부 이용자에게 체크 필요
    var memberInfo: Oauth2UserResponseDto,
)