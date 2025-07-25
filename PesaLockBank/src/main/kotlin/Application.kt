package com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import models.UserSession
import routes.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting()
        configureSessions()
    }.start(wait = true)
}

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 3600 // 1 hour
        }
    }
}