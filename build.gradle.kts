import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "com.junit"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

task<Test>("unitTest"){
    group = "verification"
    description = "containerTest"

    useJUnitPlatform {
        includeTags("unt")
        excludeTags("container","integration")
    }
}

task<Test>("containerTest") {
    group = "verification"
    description = "containerTest"
    useJUnitPlatform {
        includeTags("container")
        excludeTags("unit", "integration")
    }
}

task<Test>("integrationTest") {

    group = "verification"
    description = "integrationTest"

    useJUnitPlatform {
        includeTags("integration")
        excludeTags("unit", "container")
    }
}
