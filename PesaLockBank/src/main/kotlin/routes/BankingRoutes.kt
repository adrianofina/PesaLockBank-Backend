package routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import models.UserSession
import services.BankingService

fun Route.bankingRoutes() {
    val bankingService = BankingService()

    post("/deposit") {
        val session = call.sessions.get<UserSession>()
        if (session == null) {
            call.respondRedirect("/")
            return@post
        }

        val formParameters = call.receiveParameters()
        val amountStr = formParameters["amount"] ?: ""
        val amount = amountStr.toDoubleOrNull()

        if (amount != null) {
            val result = bankingService.deposit(session.username, amount)
            if (result.isSuccess) {
                call.respondRedirect("/dashboard?success=deposit")
            } else {
                call.respondRedirect("/deposit?error=${result.exceptionOrNull()?.message}")
            }
        } else {
            call.respondRedirect("/deposit?error=invalid_amount")
        }
    }

    post("/withdraw") {
        val session = call.sessions.get<UserSession>()
        if (session == null) {
            call.respondRedirect("/")
            return@post
        }

        val formParameters = call.receiveParameters()
        val amountStr = formParameters["amount"] ?: ""
        val amount = amountStr.toDoubleOrNull()

        if (amount != null) {
            val result = bankingService.withdraw(session.username, amount)
            if (result.isSuccess) {
                call.respondRedirect("/dashboard?success=withdraw")
            } else {
                call.respondRedirect("/withdraw?error=${result.exceptionOrNull()?.message}")
            }
        } else {
            call.respondRedirect("/withdraw?error=invalid_amount")
        }
    }
}