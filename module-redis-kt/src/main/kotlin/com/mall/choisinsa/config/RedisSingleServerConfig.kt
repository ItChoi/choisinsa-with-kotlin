package com.mall.choisinsa.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisSingleServerConfig(
    @Value("\${spring.data.redis.host}")
    private val host: String,

    @Value("\${spring.data.redis.port}")
    private val port: Int,
) {

    @Bean
    fun connectionFactory(

    ): RedisConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration(host, port))
    }

    @Bean
    fun redisTemplate(

    ): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = connectionFactory()

        val stringRedisSerializer = StringRedisSerializer()

        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = stringRedisSerializer

        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = stringRedisSerializer

        redisTemplate.setDefaultSerializer(stringRedisSerializer)
        return redisTemplate
    }

    @Bean
    fun cacheManager(

    ): RedisCacheManager {

        RedisCacheManager.builder(connectionFactory())
            .cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig()
                    //.entryTtl(Duration.ofSeconds(100)) // 캐시 만료 시간 설정
                    .disableCachingNullValues()
                    //.prefixCacheNameWith() // 캐시 이름 접두어 설정
            )
            .transactionAware()

        return RedisCacheManager.create(connectionFactory())
    }
}