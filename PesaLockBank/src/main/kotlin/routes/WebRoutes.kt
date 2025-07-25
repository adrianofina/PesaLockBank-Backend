package routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*
import models.UserSession
import services.BankingService

fun Route.webRoutes() {
    val bankingService = BankingService()

    get("/") {
        val error = call.request.queryParameters["error"]

        call.respondHtml {
            head {
                title("PesaLock Bank - Login")
                style {
                    +"""
                    body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }
                    .container { max-width: 400px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
                    input, button { padding: 12px; margin: 8px 0; width: 100%; box-sizing: border-box; border: 1px solid #ddd; border-radius: 5px; }
                    button { background-color: #4CAF50; color: white; border: none; cursor: pointer; font-size: 16px; }
                    button:hover { background-color: #45a049; }
                    .error { color: red; margin: 10px 0; padding: 10px; background-color: #ffebee; border-radius: 5px; }
                    h1 { text-align: center; color: #2E7D32; }
                    """
                }
            }
            body {
                div(classes = "container") {
                    h1 { +"🏦 PesaLock Bank" }

                    if (error != null) {
                        div(classes = "error") {
                            when (error) {
                                "invalid_credentials" -> +"Invalid username or password. Please try again."
                                else -> +"An error occurred. Please try again."
                            }
                        }
                    }

                    form(action = "/login", method = FormMethod.post) {
                        div {
                            label { +"Username:" }
                            input(type = InputType.text, name = "username") {
                                required = true
                                placeholder = "Enter your username"
                            }
                        }
                        div {
                            label { +"Password:" }
                            input(type = InputType.password, name = "password") {
                                required = true
                                placeholder = "Enter your password"
                            }
                        }
                        button(type = ButtonType.submit) { +"Login" }
                    }

                    div {
                        style = "margin-top: 20px; font-size: 12px; color: #666; text-align: center;"
                        p { +"Test accounts: warner/1234, anna/1412, fina/abcd" }
                    }
                }
            }
        }
    }

    get("/dashboard") {
        val session = call.sessions.get<UserSession>()
        if (session == null) {
            call.respondRedirect("/")
            return@get
        }

        val user = bankingService.getUserAccount(session.username)
        if (user == null) {
            call.respondRedirect("/")
            return@get
        }

        val success = call.request.queryParameters["success"]

        call.respondHtml {
            head {
                title("PesaLock Bank - Dashboard")
                style {
                    +"""
                    body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }
                    .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
                    .menu { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin: 30px 0; }
                    .menu a { 
                        padding: 20px; background-color: #4CAF50; color: white; text-decoration: none; 
                        border-radius: 8px; text-align: center; font-weight: bold; transition: background-color 0.3s;
                    }
                    .menu a:hover { background-color: #45a049; }
                    .balance { font-size: 28px; color: #2E7D32; margin: 20px 0; text-align: center; background-color: #E8F5E8; padding: 20px; border-radius: 8px; }
                    .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
                    .logout { background-color: #f44336; padding: 10px 20px; color: white; text-decoration: none; border-radius: 5px; }
                    .success { color: green; margin: 10px 0; padding: 10px; background-color: #e8f5e8; border-radius: 5px; }
                    """
                }
            }
            body {
                div(classes = "container") {
                    div(classes = "header") {
                        h1 { +"Welcome, ${user.username}!" }
                        a(href = "/logout", classes = "logout") { +"Logout" }
                    }

                    if (success != null) {
                        div(classes = "success") {
                            when (success) {
                                "deposit" -> +"Deposit successful!"
                                "withdraw" -> +"Withdrawal successful!"
                                else -> +"Operation completed successfully!"
                            }
                        }
                    }

                    div(classes = "balance") {
                        +"Current Balance: TZS ${"%.2f".format(user.balance)}"
                    }

                    div(classes = "menu") {
                        a(href = "/deposit") { +"💰 Deposit Money" }
                        a(href = "/withdraw") { +"💸 Withdraw Money" }
                        a(href = "/history") { +"📊 Transaction History" }
                    }
                }
            }
        }
    }

    // Add more routes for deposit, withdraw, history pages...
}