package com.tunahankaryagdi.b_log.data.model.comment

data class NewCommentRequest(
    val blogId: String,
    val content: String,
    val userId: String
)