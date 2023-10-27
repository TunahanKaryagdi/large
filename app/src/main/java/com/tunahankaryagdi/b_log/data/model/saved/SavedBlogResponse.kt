package com.tunahankaryagdi.b_log.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavedBlogResponse(
    val data : List<SavedBlogResponseData>,
    val message: String,
    val status: Int
)

data class SavedBlogResponseData(
    @SerializedName("blog")
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