package com.mall.choisinsa.common.enumeration

import java.util.*

enum class GenderType(
    private val desc: String,
) {
    NONE("미선택"),
    SECRET("비밀"),
    MALE("남성"),
    FEMALE("여성");

    companion object {
        fun from(type: String): GenderType? {
            for (genderType in GenderType.entries) {
                if (genderType.name.lowercase(Locale.getDefault()) == type) {
                    return genderType
                }
            }

            return null
        }
    }

}
