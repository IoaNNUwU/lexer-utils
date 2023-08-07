repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.20"
    java
    `maven-publish`
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "github.ioannuwu"
            artifactId = "lexer-utils"
            version = "1.0.1"

            from(components["java"])

            pom {
                name.set("lexer-utils")
                description.set(
                    "Kotlin library that provides easy way to split Strings to" +
                        " List of Tokens` by specified pattern"
                )
            }
        }
    }
}