package br.com.diadejava.api.koin

import br.com.diadejava.api.controller.UserController
import br.com.diadejava.api.repository.persistence.UserRepository
import org.koin.dsl.module

val userModule = module {
    single { UserController(get()) }
    single { UserRepository() }
}