package com.tunahankaryagdi.b_log.data.model.saved

data class PostSavedResponse(
    val data: PostSavedResponseData,
    val message: String
)

data class PostSavedResponseData(
    val blogId: String,
    val id: String,
    val userId: String
)