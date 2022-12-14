@file:OptIn(KtorExperimentalLocationsAPI::class)

package io.github.tuguzd.route

import io.github.tuguzd.model.Account
import io.github.tuguzd.model.toDto
import io.github.tuguzd.repository.AccountBusinessDao
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.business() {
    val dao by inject<AccountBusinessDao>()

    get<Accounts> {
        val accountList = dao.findAll().map(Account::toDto)
        call.respond(accountList)
    }

    get<Accounts.SearchLogin> {
        val accountList = dao.findByLogin(it.login).map(Account::toDto)
        call.respond(accountList)
    }

    get("/health") {
        call.respond("Healthy")
    }
}
