package com.mall.choisinsa.service

import com.mall.choisinsa.common.enumeration.RedisTTL
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService (
    private val redisTemplate: RedisTemplate<String, Any>,
) {

    /**
     * 메서드명	    레디스 타입
     * opsForValue	String
     * opsForList	List
     * opsForSet	Set
     * opsForZSet	Sorted Set
     * opsForHash	Hash
     */
    fun save(
        key: String,
        value: Any,
        ttl: RedisTTL,
    ) {
        when (value) {
            is String -> redisTemplate.opsForValue().set(key, value.toString(), Duration.ofSeconds(ttl.getSeconds()))
            is List<*> -> redisTemplate.opsForList().rightPush(key, value)
            is Set<*> -> redisTemplate.opsForSet().add(key, value)
            else -> redisTemplate.opsForValue().set(key, value.toString())
        }
    }

    fun get(
        key: String,
    ): String? {
        return redisTemplate.opsForValue().get(key)?.toString()
    }

    fun delete(
        key: String,
    ) {
        redisTemplate.delete(key)
    }

    fun hasKey(
        key: String,
    ): Boolean {
        return redisTemplate.hasKey(key)
    }
}