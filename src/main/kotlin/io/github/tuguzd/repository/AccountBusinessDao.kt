package io.github.tuguzd.repository

import io.github.tuguzd.model.Account
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.regex

class AccountBusinessDao : KoinComponent {
    private val driver by inject<AccountDriver>()
    private val collection = driver.accountCollection

    fun findAll(): List<Account> =
        collection.find().toList()

    fun findByLogin(login: String): List<Account> =
        collection.find(
            (Account::login).regex(login, "i")
        ).toList()
}
