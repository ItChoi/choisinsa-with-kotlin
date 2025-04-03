package com.mall.choisinsa.web.provider

import com.mall.choisinsa.common.domain.dto.AuthenticatedUser
import com.mall.choisinsa.common.enumeration.RedisTTL
import com.mall.choisinsa.common.enumeration.TokenType
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtTokenProvider(
    @Value("\${jwt.access-token.secret}")
    private val secretWithAccessToken: String,


    @Value("\${jwt.refresh-token.secret}")
    private val secretWithRefreshToken: String,

) {
    private val validityInMillisecondsWithAccessToken : Long = RedisTTL.ACCESS_TOKEN_TTL.time
    private val validityInMillisecondsWithRefreshToken : Long = RedisTTL.REFRESH_TOKEN_TTL.time

    fun generateToken(
        type: TokenType,
        authentication: Authentication,
        loginId: String,
    ): String {
        val extraClaims = toAuthenticatedUser(authentication).toTokenPayload()
        return buildToken(
            type,
            extraClaims,
            loginId,
        );
    }

    private fun toAuthenticatedUser(
        authentication: Authentication,
    ): AuthenticatedUser {
        require(authentication != null
                && authentication.principal != null
                && authentication.principal is AuthenticatedUser)
        return authentication.principal as AuthenticatedUser
    }

    private fun buildToken(
        type: TokenType,
        extraClaims: MutableMap<String, Any?>,
        loginId: String,
    ): String {
        val now = System.currentTimeMillis()
        val issuedAt = Date(now) // 발급 시간
        val expiration = Date(now + getExpirationTime(type)) // 15분 후 만료

        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(loginId)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
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

    fun isValidToken(
        type: TokenType,
        token: String,
    ): Boolean {
        return !isTokenExpired(type, token)
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