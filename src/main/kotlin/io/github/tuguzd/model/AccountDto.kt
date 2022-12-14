package io.github.tuguzd.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val id: String? = null,
    val login: String,
    val password: String,
)

fun AccountDto.toAccount() =
    Account(
        login = this.login,
        password = this.password,
    )
