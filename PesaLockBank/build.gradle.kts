plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    application
}

group = "com.pesalockbank"
version = "1.0.0"

application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
  implementation(libs.ktor.server.core)
implementation(libs.ktor.server.netty)
implementation(libs.ktor.serialization.kotlinx.json)
implementation(libs.ktor.server.content.negotiation)
implementation(libs.postgresql)
implementation(libs.h2)
implementation(libs.logback.classic)
implementation(libs.ktor.server.config.yaml)
implementation(libs.ktor.server.sessions)
implementation(libs.ktor.server.html.builder)
implementation(libs.kotlinx.html.jvm)
testImplementation(libs.ktor.server.test.host)
testImplementation(libs.kotlin.test.junit)
}