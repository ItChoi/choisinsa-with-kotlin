package com.mall.choisinsa.common.enumeration

enum class RedisTTL(
    private val timeUnit: TimeUnit,
    val time: Long,
) {
    DEFAULT_TTL(TimeUnit.MINUTES, 5),
    ACCESS_TOKEN_TTL(TimeUnit.MILLISECONDS, 900000),
    REFRESH_TOKEN_TTL(TimeUnit.MILLISECONDS, 8640000),
    TTL_SECONDS(TimeUnit.MINUTES, 1209600),
    TEST(TimeUnit.SECONDS, 15);


    fun getSeconds(): Long {
        return timeUnit.getSeconds(time)
    }
}