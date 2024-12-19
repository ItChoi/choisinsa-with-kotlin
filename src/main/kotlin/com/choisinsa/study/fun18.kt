package com.choisinsa.study

import com.choisinsa.study.data.Fruit as FruitA
import com.choisinsa.study.Fruit as Fruit

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("바나나", 2500),
        Fruit("수박", 10000)
    )

    val person = Person("최태현", 100)
    //val (age, name) = person
    val name = person.component1()
    val age = person.component2()

    val numbers = listOf(1, 2, 3)
    numbers.map {number -> number + 1}
        .forEach {number -> println(number)}

    run {
        numbers.forEach {number ->
            if (number == 2) {
                return@run //break
                return@forEach // continue
            }
        }
    }


}

private fun filterFruits(
    fruit: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    return fruit.filter(filter)
}