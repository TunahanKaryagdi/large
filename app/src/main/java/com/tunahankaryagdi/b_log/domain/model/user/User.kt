package com.tunahankaryagdi.b_log.domain.model.user

import com.tunahankaryagdi.b_log.data.model.user.UserDto


data class User(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val role: String
)


fun UserDto.toUser() : User{
    return User(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        role = this.role
    )

}

