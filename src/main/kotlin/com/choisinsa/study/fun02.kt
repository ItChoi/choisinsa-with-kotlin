package com.choisinsa.study

import java.lang.IllegalArgumentException

fun main() {
    val str: String? = "ABC"
    //str.length
    println(str?.length)

    str?.length ?: 0 // length가 없다면 0을 대입한다.
    println(startWith("ABC"))

}

fun startWithA1(str: String?): Boolean {
    if (str == null) {
        throw IllegalArgumentException("null이 들어왔습니다.")
    }

    return str.startsWith("A")
}

fun startWithA11(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null이 들어왔따")
}

fun startWithA2(str: String?): Boolean? {
    if (str == null) {
        return null
    }

    return str.startsWith("A")
}

fun startWithA22(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startWithA3(str: String?): Boolean {
    //str.startsWith("A")
    if (str == null) {
        return false
    }

    return str.startsWith("A")
}

fun startWith(str: String?): Boolean {
    return str!!.startsWith("A")
}