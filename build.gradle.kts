plugins {
    kotlin("jvm") version "2.3.0"
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

kotlin {
    jvmToolchain(21)
}