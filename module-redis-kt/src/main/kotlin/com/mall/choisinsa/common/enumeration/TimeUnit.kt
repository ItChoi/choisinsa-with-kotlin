package com.mall.choisinsa.common.enumeration

import java.time.Year
import java.time.YearMonth

enum class TimeUnit(
    private val desc: String,
) {
    MILLISECONDS("밀리 초 단위"),
    SECONDS("초 단위"),
    MINUTES("분 단위"),
    HOURS("시간 단위"),
    DAYS("일 단위"),
    WEEKLY("주 단위"),
    MONTHS("달 단위"),
    YEARS("년 단위");

    fun getSeconds(time: Long): Long {
        return when (this) {
            MILLISECONDS -> time / 1000
            SECONDS -> time
            MINUTES -> time * 60
            HOURS -> time * 3600
            DAYS -> time * 86400
            WEEKLY -> time * 604800
            MONTHS -> getDaysInCurrentMonth() * 86400
            YEARS -> getDaysInCurrentYear() * 86400
        } - 1
    }

    // 이번 달의 일 수 계산
    private fun getDaysInCurrentMonth(): Long {
        return YearMonth.now().lengthOfMonth().toLong()
    }

    // 이번 년도의 일 수 계산
    fun getDaysInCurrentYear(): Long {
        return if (Year.now().isLeap) 366L else 365L
    }
}