@file:OptIn(KtorExperimentalLocationsAPI::class)

package io.github.tuguzd.route

import io.github.tuguzd.model.*
import io.github.tuguzd.repository.AccountCrudDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import org.koin.ktor.ext.inject

fun Route.crud() {
    val dao by inject<AccountCrudDao>()

    post<Accounts.Create> {
        val request = call.receive<AccountDto>()
        val account = request.toAccount()

        dao.create(account)?.let { id ->
            call.apply {
                response.headers.append("My-User-Id-Header", id.toString())
                respond(HttpStatusCode.Created)
            }
        } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }

    put<Accounts.Update> {
        val request = call.receive<AccountDto>()
        val account = request.toAccount()

        if (dao.updateById(it.id, account)) call.respond(HttpStatusCode.NoContent)
        else call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
    }

    get<Accounts.Read> {
        dao.findById(it.id)
            ?.let { account -> call.respond(account.toDto()) }
            ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }

    delete<Accounts.Delete> {
        if (dao.deleteById(it.id)) call.respond(HttpStatusCode.NoContent)
        else call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
    }
}
