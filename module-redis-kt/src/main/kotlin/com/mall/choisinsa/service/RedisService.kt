package com.mall.choisinsa.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.SortedSet

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
        expiredSeconds: Long?
    ) {
        // TODO: expiredSeconds: Long? = null -> 만료 시간 0으로 레디스 내에서 저장하면 에러 발생, null인 경우 default 시간 또는 처리 로직 필요
        when (value) {
            is String -> redisTemplate.opsForValue().set(key, value.toString())
            else -> redisTemplate.opsForValue().set(key, value.toString())
        }
    }

}