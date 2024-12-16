package com.choisinsa.study

fun validateScoreIsNotNegative01(score: Int) {
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0 보다 작을 수 없습니다.")
    }
}

fun validateScoreIsNotNegative02(score: Int): Unit {
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0 보다 작을 수 없습니다.")
    }
}

fun getPassOrFail(score: Int): String {
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

fun getGrade(score: Int): String {
    return if (score >= 90) {
        "A"
    } else if (score >= 80) {
        "B"
    } else if (score >= 70) {
        "C"
    } else {
        "D"
    }
}

fun test1(score: Int) {
    if (score !in 0..100) {
        throw IllegalArgumentException("score 범위는 ~ 입니다")
    }
}

fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun getGradeWithSwitch1(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "A"
        in 70..79 -> "A"
        else -> "D"
    }
}

fun startWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun JudgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("많이 본 숫자")
        else -> println("1, 0, -1이 아닙니다.")
    }
}

fun JudgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0 입니다")
        number % 2 == 0 -> println("주어진 숫자 짝수")
        else -> println("홀수")
    }
}
