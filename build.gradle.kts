import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("kapt") version "1.9.25"
}

group = "com"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	
}

allprojects {
	// 플러그인 적용
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "java")

	repositories {
		mavenCentral()
	}

	// Java 버전 설정 (Java Toolchain 사용)
	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
			//languageVersion = JavaLanguageVersion.of(17)
		}
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {
	// 플러그인 적용
	apply(plugin = "org.jetbrains.kotlin.kapt")

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")

		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}
}

tasks.withType<Jar> {
	enabled = true
}
tasks.withType<BootJar> {
	enabled = false
}
