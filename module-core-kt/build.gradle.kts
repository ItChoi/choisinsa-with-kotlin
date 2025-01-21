plugins {
    //id("org.springframework.boot") version "3.4.0" apply false // Spring Boot 플러그인은 여기서 비활성화
    id("org.springframework.boot") apply false // Spring Boot 플러그인은 여기서 비활성화

    kotlin("plugin.jpa") version "1.9.25"
}

group = "choisinsa.module-core-kt"
version = "0.0.1-SNAPSHOT"

dependencies {
//    implementation(project(":module-redis-kt"))
//    implementation(project(":module-common-kt"))

    // QueryDSL 의존성 추가
    //implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

    //testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    //testImplementation("org.springframework.security:spring-security-test")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

// Querydsl 설정부 추가 - start
val generated = file("src/main/generated")

// querydsl QClass 파일 생성 위치를 지정
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(generated)
}

// kotlin source set 에 querydsl QClass 위치 추가
sourceSets {
    main {
        kotlin.srcDirs += generated
    }
}

// gradle clean 시에 QClass 디렉토리 삭제
tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}


kapt {
    generateStubs = true
}
// Querydsl 설정부 추가 - end

tasks {
    // Spring Boot의 bootJar를 비활성화하고, 일반 JAR만 생성하도록 설정
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}