package br.com.diadejava.api.application

import br.com.diadejava.api.controller.UserController
import br.com.diadejava.api.koin.userModule
import br.com.diadejava.api.repository.persistence.UserTable
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class RestApplication : KoinComponent {
    private val userController by inject<UserController>()

    fun startApplication() {
        Javalin.create { config ->
            config.defaultContentType = "application/json"
        }.apply {
            routes {
                ApiBuilder.path("users") {
                    ApiBuilder.get(userController::getAll)
                    ApiBuilder.post(userController::create)
                }
            }
        }.start(7000)
    }

    fun connectDatabase() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
    }
}

fun main() {
    startKoin {
        modules(userModule)
    }

    RestApplication().connectDatabase()
    RestApplication().startApplication()
}