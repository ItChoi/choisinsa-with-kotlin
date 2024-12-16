package com.choisinsa.study

class Penguin(
    species: String
) : Animal(species, 2), Flyable {
    private val wingCount: Int = 2

    override fun move() {
        println("~펭귄 움직~")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun fly() {
        super<Flyable>.act()
    }
}