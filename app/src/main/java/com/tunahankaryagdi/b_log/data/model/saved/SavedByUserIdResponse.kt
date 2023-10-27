package com.tunahankaryagdi.b_log.data.model.saved

data class GetSavedByUserIdResponse(
    val data : List<GetSavedByUserIdResponseData>,
    val message: String,
    val status: Int
)

data class GetSavedByUserIdResponseData(
    val savedBlogDto: SavedBlogDto,
    val blogId: String,
    val id: String,
    val userId: String
)

data class SavedBlogDto(
    val authorId: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val published: Boolean,
    val searchable_text: String,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)