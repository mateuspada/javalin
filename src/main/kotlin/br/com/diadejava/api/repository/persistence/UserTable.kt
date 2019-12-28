package br.com.diadejava.api.repository.persistence

import org.jetbrains.exposed.sql.Table

private const val ID_LENGTH = 26
private const val USER_NAME_LENGTH = 200

object UserTable : Table("USER") {
    val id = varchar("ID", ID_LENGTH).primaryKey()
    val name = varchar("NAME", USER_NAME_LENGTH)
}