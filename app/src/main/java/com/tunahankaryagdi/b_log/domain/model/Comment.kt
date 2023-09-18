package com.tunahankaryagdi.b_log.domain.model

import com.tunahankaryagdi.b_log.data.model.CommentDto
import com.tunahankaryagdi.b_log.data.model.UserDto


data class Comment(
    val id: String,
    val content: String,
    val user: User,
    val updatedAt: String
)

fun CommentDto.toComment() : Comment{
    return Comment(
        id = this.id,
        content = this.content,
        user = this.user.toUser(),
        updatedAt = this.updatedAt
    )
}


data class User(
    val id: String,
    val firstName: String,
    val lastName: String
)


fun UserDto.toUser(): User{
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName
    )
}