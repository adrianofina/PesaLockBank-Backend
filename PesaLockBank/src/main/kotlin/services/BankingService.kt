package services

import models.UserAccount
import models.userAccounts

class BankingService {

    fun getUserAccount(username: String): UserAccount? {
        return userAccounts.find { it.username == username }
    }

    fun deposit(username: String, amount: Double): Result<UserAccount> {
        val user = getUserAccount(username) ?: return Result.failure(Exception("User not found"))

        if (amount <= 0) {
            return Result.failure(Exception("Invalid amount"))
        }

        user.balance += amount
        val transaction = "Deposited TZS ${"%.2f".format(amount)} - Balance: TZS ${"%.2f".format(user.balance)}"
        user.transactionHistory.add(transaction)

        return Result.success(user)
    }

    fun withdraw(username: String, amount: Double): Result<UserAccount> {
        val user = getUserAccount(username) ?: return Result.failure(Exception("User not found"))

        if (amount <= 0) {
            return Result.failure(Exception("Invalid amount"))
        }

        if (amount > user.balance) {
            return Result.failure(Exception("Insufficient funds"))
        }

        user.balance -= amount
        val transaction = "Withdrew TZS ${"%.2f".format(amount)} - Balance: TZS ${"%.2f".format(user.balance)}"
        user.transactionHistory.add(transaction)

        return Result.success(user)
    }
}