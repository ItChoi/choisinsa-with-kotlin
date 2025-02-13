package com.mall.choisinsa.common.enumeration

enum class RedisTTL(
    private val timeUnit: TimeUnit,
    private val time: Long,
) {
    DEFAULT_TTL(TimeUnit.MINUTES, 5);

    fun getSeconds(): Long {
        return timeUnit.getSeconds(time)
    }
}