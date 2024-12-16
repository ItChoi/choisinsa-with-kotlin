package com.choisinsa.study

class Cat(
    species: String
) : Animal(species, 4) {
    override fun move() {
        println("~고양이 사뿐사뿐~")
    }
}