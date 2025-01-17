package com.mall.choisinsa.web.provider

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.Date


@Component
class JwtTokenProvider(
    @Value("\${jwt.access-token.secret}")
    private val secretWithAccessToken: String,

    @Value("\${jwt.access-token.validity-in-milliseconds}")
    private val validityInMillisecondsWithAccessToken : Long,

    @Value("\$jwt.refresh-token.secret}")
    private val secretWithRefreshToken: String,

    @Value("\${jwt.refresh-token.validity-in-milliseconds}")
    private val validityInMillisecondsWithRefreshToken : Long,
) {
    /**
     * TODO
     * 1. 토큰 제어 로직 추가
     * 2. 리프레시 토큰 추가
     * 3. 리프레시, 액세스 토큰 로직 재사용 리팩토링
     */

    fun generateToken(
        extraClaims: MutableMap<String, Any?>,
        userDetails: UserDetails,
    ): String {
        return buildToken(extraClaims, userDetails, validityInMillisecondsWithAccessToken);
    }

    private fun buildToken(
        extraClaims: MutableMap<String, Any?>,
        userDetails: UserDetails,
        expiration: Long,
    ): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }


    private fun getSignInKey(): Key {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretWithAccessToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}