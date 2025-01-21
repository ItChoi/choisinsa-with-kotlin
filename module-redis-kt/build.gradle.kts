group = "choisinsa.module-redis-kt"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

tasks {
    // Spring Boot의 bootJar를 비활성화하고, 일반 JAR만 생성하도록 설정
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}