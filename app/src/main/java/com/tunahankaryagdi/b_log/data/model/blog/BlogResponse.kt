package com.tunahankaryagdi.b_log.data.model.blog

import com.google.gson.annotations.SerializedName


data class BlogResponse(
    @SerializedName("data")
    val blogs: List<BlogDto>,
    val message: String,
    val status: Int
)


data class BlogDto(
    val author: AuthorDto,
    val authorId: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val likes: List<LikeDto>,
    val published: Boolean,
    @SerializedName("searchable_text")
    val searchableText: String,
    val sections: List<SectionDto>,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)



data class LikeDto(
    val userId: String
)

data class SectionDto(
    val blogId: String,
    val content: String,
    val id: String,
    val image: String,
    val title: String,
    val type: String
)

data class AuthorDto(
    val id : String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String
)