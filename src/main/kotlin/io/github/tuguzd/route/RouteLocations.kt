@file:OptIn(KtorExperimentalLocationsAPI::class)

package io.github.tuguzd.route

import io.ktor.server.locations.*

@Location("/accounts")
class Accounts {
    @Location("")
    data class Create(val account: Accounts)

    @Location("")
    data class Read(val id: String, val account: Accounts)

    @Location("")
    data class Update(val id: String, val account: Accounts)

    @Location("")
    data class Delete(val id: String, val account: Accounts)

    @Location("/search")
    data class SearchLogin(val login: String, val account: Accounts)
}
