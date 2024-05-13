package com.ztpai2024.timewisepro.repositories

import com.ztpai2024.timewisepro.entities.User
import com.ztpai2024.timewisepro.entities.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    fun findById(id: Int): UserDto? {
        return transaction {
            User.findById(id)?.toDto()
        }
    }

    fun findByEmail(email: String): User? {
        return transaction {
            User.findByEmail(email)
        }
    }

    fun findAll(): List<User> {
        return transaction {
            User.all().toList()
        }
    }

    fun save(user: User): User {
        return transaction {
            user.flush()
            user
        }
    }

    fun deleteById(id: Int) {
        transaction {
            val user = User.findById(id)
            user?.delete()
        }
    }
}


data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    val surname: String
)

fun User.toDto() = UserDto(
    id = id.value,
    name = userDetails.name,
    surname = userDetails.surname,
    email = userEmail,
)