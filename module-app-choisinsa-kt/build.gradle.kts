import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.asciidoctor.jvm.convert") version "4.0.3"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "choisinsa.module-app-choisinsa-kt"
version = "0.0.1-SNAPSHOT"

val asciidoctorExt: Configuration by configurations.creating
val snippetsDir by extra { file("build/generated-snippets") }

dependencies {
    implementation(project(":module-core-kt"))
    implementation(project(":module-common-kt"))
    implementation(project(":module-redis-kt"))

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

    // asciidoctor
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    // AWS KMS SDK
    //implementation("software.amazon.awssdk:kms:2.20.0")

    // Kotlin standard + Jackson or any other libraries
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("com.h2database:h2:1.4.200")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.security:spring-security-test")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

// Ascii Doc Create Tasks
tasks {
    test {
        outputs.dir(snippetsDir) // 필요 시 명시
    }

    // 기존에 존재하는 Docs 삭제(문서 최신화를 위해)
    clean {
        delete(file("src/main/resources/static/docs"))
    }

    asciidoctor {
        // snippet Directory 설정
        inputs.dir(snippetsDir)
        configurations(asciidoctorExt.name)
        // test 가 성공해야만, 아래 Task 실행
        dependsOn(test)

        // adoc 개별 파일들의 경로를 baseDir로 설정
        baseDirFollowsSourceFile()
    }

    // Ascii Doc 파일 생성
    copy {
        from("build/docs/asciidoc")
        into("src/main/resources/static/docs")
    }

    jar {
        enabled = false
    }

    bootJar {
        dependsOn(asciidoctor)
        from ("build/docs/asciidoc") {
            into("static/docs")
        }
    }
}