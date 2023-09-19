package com.tunahankaryagdi.b_log.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("data")
    val comments: List<CommentDto>,
    val message: String,
    val status: Int
)


data class CommentDto(
    val blogId: String,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val user: UserDto,
    val userId: String
)

data class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String
)