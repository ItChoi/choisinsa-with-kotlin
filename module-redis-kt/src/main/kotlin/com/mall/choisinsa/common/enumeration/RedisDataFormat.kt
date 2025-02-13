package com.mall.choisinsa.common.enumeration

enum class RedisDataFormat(
    private val desc: String,
    private val unique: String,
) {
    ACCESS_TOKEN("액세스 토큰", "accessToken"),
    REFRESH_TOKEN("리프레시 토큰", "refreshToken");

    fun formattedKey(
        key: String,
    ): String {
        return formattedKey(mutableListOf(key))
    }

    fun formattedKey(
        keys: MutableList<String>
    ): String {
        when (this) {
            ACCESS_TOKEN -> {
                keys.add(this.unique)
            }
            REFRESH_TOKEN -> {
                keys.add(this.unique)
            }
        }

        return formatted(keys)
    }
    fun formattedValue(
        values: MutableList<String>
    ): String {
        return formatted(values)
    }

    private fun formatted(
        values: MutableList<String>
    ): String {
        return values.joinToString(":")
    }
}