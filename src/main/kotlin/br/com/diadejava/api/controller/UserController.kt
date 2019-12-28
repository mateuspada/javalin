package br.com.diadejava.api.controller

import br.com.diadejava.api.model.User
import br.com.diadejava.api.repository.persistence.UserRepository
import io.azam.ulidj.ULID
import io.javalin.http.Context
import org.apache.logging.log4j.LogManager

class UserController(private val userRepository: UserRepository) {

    private val logger = LogManager.getLogger(UserController::class.java)

    fun getAll(ctx: Context) {
        logger.info("Getting all users")
        ctx.json(userRepository.getAll())
    }

    fun create(ctx: Context) {
        logger.info("Creating user")
        ctx.json(
            userRepository.create(
                User(
                    id = ULID.random(),
                    name = "Mateus Spada Leme"
                )
            )
        )
    }

}