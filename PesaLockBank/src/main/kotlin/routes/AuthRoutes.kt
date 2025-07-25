package routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import models.*

fun Route.authRoutes() {

    post("/login") {
        val formParameters = call.receiveParameters()
        val username = formParameters["username"] ?: ""
        val password = formParameters["password"] ?: ""

        val validCredential = userCredentials.find { it.first == username && it.second == password }

        if (validCredential != null) {
            call.sessions.set(UserSession(username))
            call.respondRedirect("/dashboard")
        } else {
            call.respondRedirect("/?error=invalid_credentials")
        }
    }

    get("/logout") {
        call.sessions.clear<UserSession>()
        call.respondRedirect("/")
    }
}