import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "choisinsa.module-app-choisinsa-kt"
version = "0.0.1-SNAPSHOT"

extra["snippetsDir"] = file("build/generated-snippets")

val asciidoctorExt by configurations.creating



dependencies {
    // asciidoctor
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    implementation(project(":module-core-kt"))
    implementation(project(":module-common-kt"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    // QueryDSL 의존성 추가
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // oauth2
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")


    runtimeOnly("com.h2database:h2:1.4.200")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
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
    configurations("asciidoctorExt")
    dependsOn(tasks.test)
}

// TODO
tasks.register<Copy>("copyDocument") { // (5)
    dependsOn(tasks.named("asciidoctor"))
    from(file("build/docs/asciidoc"))
    into(file("src/main/resources/static/docs"))
}

tasks.named<BootJar>("bootJar") {
    dependsOn(tasks.named("asciidoctor")) // asciidoctor 태스크 실행 후 실행되도록 의존성 추가
    //dependsOn("asciidoctor") // asciidoctor 태스크 실행 후 실행되도록 의존성 추가
    //from(tasks.named("asciidoctor").map { it.outputs.files.singleFile }) { // asciidoctor 출력 디렉터리 동적으로 참조
    //from(tasks.named("asciidoctor").map { it.outputs.files.singleFile }) { // asciidoctor 출력 디렉터리 동적으로 참조
    from("${tasks.named("asciidoctor").get().outputs.files.singleFile}/html5") { // asciidoctor 출력 디렉터리 동적으로 참조
        into("static/docs") // BootJar 내에 포함될 경로
    }
}

tasks.jar {
    enabled = false
}
