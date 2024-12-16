package com.choisinsa.study

// void 생략 -> 코틀린은 void 대신 Unit 사용
fun validateScoreIsNotNegative(score: Int) {
    if (score < 0) {
        throw IllegalArgumentException("${score} 0 보다 작을 수 없다.")
    }

    if (score !in 0..100) {
        throw IllegalArgumentException("${score}")
    }

}

fun getPassOrFail1(score: Int): String {
    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

fun getGrade1(score: Int): String {
    if (score >= 90) {
        return "A"
    } else if (score >= 80) {
        return "B"
    } else if (score >= 70) {
        return "C"
    } else {
        return "D"
    }
}
