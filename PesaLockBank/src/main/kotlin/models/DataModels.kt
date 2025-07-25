package models

import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    val username: String,
    var balance: Double = 0.0,
    val transactionHistory: MutableList<String> = mutableListOf()
)

@Serializable
data class UserSession(
    val username: String
)

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class TransactionRequest(
    val amount: Double
)

// Your user credentials and accounts data
val userCredentials = listOf(
    Pair("warner", "1234"),
    Pair("finna", "abcd"),
    Pair("anna", "1412"),
    Pair("adriano", "xyz"),
    Pair("admin1", "admin")
)

val userAccounts = mutableListOf(
    UserAccount("warner", 150000.0),
    UserAccount("anna", 300000.0),
    UserAccount("fina", 720000.0),
    UserAccount("adriano", 8000000.0),
    UserAccount("admin1", 1050000.0)
)