repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.20"
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}