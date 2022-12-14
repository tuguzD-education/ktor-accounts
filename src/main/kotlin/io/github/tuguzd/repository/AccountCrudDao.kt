package io.github.tuguzd.repository

import io.github.tuguzd.model.Account
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class AccountCrudDao : KoinComponent {
    private val driver by inject<AccountDriver>()
    private val collection = driver.accountCollection

    fun create(account: Account): Id<Account>? {
        collection.insertOne(account)
        return account.id
    }

    fun findById(id: String): Account? =
        collection.findOne(Account::id eq ObjectId(id).toId())

    fun updateById(id: String, request: Account): Boolean =
        findById(id)?.let { account ->
            collection.replaceOne(
                account.copy(
                    login = request.login,
                    password = request.password,
                )
            ).modifiedCount == 1L
        } ?: false

    fun deleteById(id: String): Boolean =
        collection.deleteOneById(ObjectId(id)).deletedCount == 1L
}
