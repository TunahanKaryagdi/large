package com.tunahankaryagdi.b_log.domain.model.comment

import com.tunahankaryagdi.b_log.data.model.comment.CommentDto
import com.tunahankaryagdi.b_log.data.model.comment.UserDto


data class Comment(
    val id: String,
    val content: String,
    val user: User,
    val updatedAt: String
)

data class User(
    val id: String,
    val firstName: String,
    val lastName: String
)

fun CommentDto.toComment(): Comment{
    return Comment(
        id = this.id,
        content = this.content,
        updatedAt =this.updatedAt,
        user = this.user.toUser()
    )
}


fun UserDto.toUser(): User{
    return User(
        id = this.id,
        firstName =this.firstName,
        lastName = this.lastName
    )
}

