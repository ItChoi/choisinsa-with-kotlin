package com.choisinsa.study

abstract class Animal(
    protected val spcies: String,
    protected open val legCount: Int, // 프로퍼티 사용시 open 키워드 추가
) {

    abstract fun move()
}