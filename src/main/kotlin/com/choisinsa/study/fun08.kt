package com.choisinsa.study

fun main() {
    printAll("a", "b", "c")

    val array = arrayOf("a", "b", "c")
    printAll(*array)
}

fun max(a: Int, b: Int): Int {
    if (a > b) {
        return a
    }

    return b
}

fun max1(a: Int, b: Int): Int =
    if (a > b) {
        a
    } else {
        b
    }

fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}