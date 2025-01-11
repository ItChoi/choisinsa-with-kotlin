plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "choisinsa.module-auth-kt"
version = "0.0.1-SNAPSHOT"

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation(project(":module-redis-kt"))
    implementation(project(":module-common-kt"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}
