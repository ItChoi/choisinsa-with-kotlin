package com.choisinsa.study

fun main() {
    val array = arrayOf(100, 200)

    for (i in array.indices) {
        println("${i} ${array[i]}")
    }

    for ((idx, value) in array.withIndex()) {
        println("$idx $value")
    }

    array.plus(300)

    printNumbers(emptyList())
    // emptyList<>()

    println(array.get(0))
    println(array[0])

    val oldMap = mutableMapOf<Int, String>()
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"

    mapOf(1 to "MONDAY", 2 to "TUESDAY")

    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((key, value) in oldMap.entries) {
        println(key)
        println(value)
    }

}

private fun printNumbers(numbers: List<Int>) {

}