plugins {
    kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "8.3.3"
}

group = "rip.sunrise"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:3.4.2")
    implementation("io.ktor:ktor-server-netty:3.4.2")
    implementation("io.ktor:ktor-network-tls-certificates:3.4.2")
}

tasks {
    jar {
        manifest.attributes(
            "Manifest-Version" to 1.0,
            "Main-Class" to "rip.sunrise.oringo.MainKt",
        )
    }

    build {
        finalizedBy(shadowJar)
    }
}

kotlin {
    jvmToolchain(21)
}