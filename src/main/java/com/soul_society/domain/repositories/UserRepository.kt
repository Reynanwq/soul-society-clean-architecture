package com.soul_society.domain.repositories

import com.soul_society.domain.entities.User
import java.util.Optional
import java.util.UUID

/**
 * Interface de porta de saída (output port) para persistência de Users
 */
interface UserRepository {

    fun save(user: User): User

    fun findById(id: UUID): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun existsByEmail(email: String): Boolean

    fun deleteById(id: UUID)
}