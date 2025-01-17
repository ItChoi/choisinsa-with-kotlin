package com.mall.choisinsa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class ChoisinsaApplication

fun main(args: Array<String>) {
    runApplication<ChoisinsaApplication>(*args)
}
