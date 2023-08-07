repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.20"
    `maven-publish`
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.ioannuwu"
            artifactId = "lexer-utils"
            version = "1.0"
        }
    }
}