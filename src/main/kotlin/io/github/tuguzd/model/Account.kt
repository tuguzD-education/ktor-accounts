package io.github.tuguzd.model

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Account(
    @BsonId
    val id: Id<Account>? = null,
    var login: String,
    var password: String,
)

fun Account.toDto() =
    AccountDto(
        id = this.id.toString(),
        login = this.login,
        password = this.password,
    )
