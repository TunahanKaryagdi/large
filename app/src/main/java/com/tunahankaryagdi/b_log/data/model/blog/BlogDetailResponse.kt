package com.tunahankaryagdi.b_log.data.model.blog

import com.google.gson.annotations.SerializedName

data class BlogDetailResponse(
    @SerializedName("data")
    val blogDetail: BlogDetailDto,
    val message: String,
    val status: Int
)

data class BlogDetailDto(
    val author: AuthorDetailDto,
    val authorId: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val likes: List<LikeDetailDto>,
    val published: Boolean,
    @SerializedName("searchable_text")
    val searchableText: String,
    val sections: List<SectionDetailDto>,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)


data class SectionDetailDto(
    val blogId: String,
    val content: String,
    val id: String,
    val image: String,
    val title: String,
    val type: String
)


data class AuthorDetailDto(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String
)

data class LikeDetailDto(
    val userId: String
)