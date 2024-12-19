package com.choisinsa.study

import kotlin.contracts.contract

//class Person constructor(name: String, age: Int) {
//class Person(name: String, age: Int) {
data class Person(
    // 주 생성자 -> 반드시 존재, 기본 값을 여기다가 추가해서 사용하는 것을 권장
    val name: String,
    var age: Int
) {
//    val name: String = name
//    var age: Int = age
//    val name = name
//    var age = age

    // 검증
    init {
        // 클래스 초기화 시점에 호출되는 블록 -> 검증 로직 추가 가능
        if (age <= 0) {
            throw IllegalArgumentException("${age} 나이는 XXX")
        }
    }

    // 부 생성자 -> 옵션 사용 가능 -> 최종적으로 주 생성자를 this로 호출해야 한다.
    constructor(name: String) : this(name, 1) {
        println("1 - 부 생성자")
    }
    constructor(): this("홍길동") {
        println("2 - 부 생성자")
    }
}

fun main() {
    val person = Person("최태현", 100)
    println(person.name) // getter
    person.age = 10 // setter
    println(person.age)
}
