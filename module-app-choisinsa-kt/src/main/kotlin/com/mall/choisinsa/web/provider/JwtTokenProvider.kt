package com.mall.choisinsa.web.provider

import com.mall.choisinsa.common.enumeration.TokenType
import com.mall.choisinsa.dto.response.TokenResponseDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtTokenProvider(
    @Value("\${jwt.access-token.secret}")
    private val secretWithAccessToken: String,

    @Value("\${jwt.access-token.validity-in-milliseconds}")
    private val validityInMillisecondsWithAccessToken : Long,

    @Value("\${jwt.refresh-token.secret}")
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
        type: TokenType,
        extraClaims: MutableMap<String, Any?>,
        loginId: String,
    ): String {
        return buildToken(
            type,
            extraClaims,
            loginId,
        );
    }

    fun generateToken(
        extraClaims: MutableMap<String, Any?>,
        loginId: String,
    ): TokenResponseDto {
        return TokenResponseDto(
            generateToken(TokenType.ACCESS_TOKEN, extraClaims, loginId),
            generateToken(TokenType.REFRESH_TOKEN, extraClaims, loginId),
        )
    }

    private fun buildToken(
        type: TokenType,
        extraClaims: MutableMap<String, Any?>,
        loginId: String,
    ): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(loginId)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + getExpirationTime(type)))
            .signWith(getSignInKey(type), SignatureAlgorithm.HS512)
            .compact();
    }

    private fun getSignInKey(type: TokenType): Key {
        val secret = when (type) {
            TokenType.ACCESS_TOKEN -> secretWithAccessToken
            TokenType.REFRESH_TOKEN -> secretWithRefreshToken
        }

        val keyBytes: ByteArray = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    fun extractUsername(
        type: TokenType,
        token: String
    ): String {
        return extractClaim(type, token, Claims::getSubject)
    }

    fun <T> extractClaim(
        type: TokenType,
        token: String,
        claimsResolver: (Claims) -> T
    ): T {
        val claims = extractAllClaims(type, token)
        return claimsResolver(claims)
    }

    fun getExpirationTime(type: TokenType): Long {
        return when (type) {
            TokenType.ACCESS_TOKEN -> validityInMillisecondsWithAccessToken
            TokenType.REFRESH_TOKEN -> validityInMillisecondsWithRefreshToken
        }
    }

    fun isTokenValid(
        type: TokenType,
        token: String,
        loginId: String,
    ): Boolean {
        val username = extractUsername(type, token)
        return username == loginId && !isTokenExpired(type, token)
    }

    private fun isTokenExpired(
        type: TokenType,
        token: String,
    ): Boolean {
        return extractExpiration(type, token).before(Date())
    }

    private fun extractExpiration(
        type: TokenType,
        token: String
    ): Date {
        return extractClaim(type, token, Claims::getExpiration)
    }

    private fun extractAllClaims(
        type: TokenType,
        token: String,
    ): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey(type))
            .build()
            .parseClaimsJws(token)
            .body
    }
}