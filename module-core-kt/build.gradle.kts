plugins {
    kotlin("plugin.jpa") version "1.9.25"
}

group = "choisinsa.module-core-kt"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":module-redis-kt"))
    implementation(project(":module-common-kt"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}