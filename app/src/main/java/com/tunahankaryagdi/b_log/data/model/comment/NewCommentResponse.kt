package com.tunahankaryagdi.b_log.data.model.comment

import com.google.gson.annotations.SerializedName

data class NewCommentResponse(
    @SerializedName("data")
    val newComment: NewComment,
    val message: String
)


data class NewComment(
    val blogId: String,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val userId: String
)