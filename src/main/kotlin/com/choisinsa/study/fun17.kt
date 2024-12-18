package com.choisinsa.study

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

    // 이름없는 함수, 람다
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    //
    val isApple2 = { fruit: Fruit -> fruit.name == "사과"}

    isApple(fruits[0])
    isApple.invoke(fruits[0])

    filterFruits(fruits, isApple)
    filterFruits(fruits, isApple2)
    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과"})
}

private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }

    return results
}