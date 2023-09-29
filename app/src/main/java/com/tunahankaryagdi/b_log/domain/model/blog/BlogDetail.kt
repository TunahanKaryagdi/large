package com.tunahankaryagdi.b_log.domain.model.blog

import com.google.gson.annotations.SerializedName
import com.tunahankaryagdi.b_log.data.model.blog.AuthorDetailDto
import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailDto
import com.tunahankaryagdi.b_log.data.model.blog.LikeDetailDto
import com.tunahankaryagdi.b_log.data.model.blog.SectionDetailDto


data class BlogDetail(
    val id: String,
    val author: AuthorDetail,
    val createdAt : String,
    val image: String,
    val likes: List<LikeDetail>,
    val published: Boolean,
    val searchable_text: String,
    val sections: List<SectionDetail>,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)

fun BlogDetailDto.toBlogDetail() : BlogDetail {
    return BlogDetail(
        id = this.id,
        author = this.author.toAuthorDetail(),
        createdAt = this.createdAt,
        image = this.image,
        likes = this.likes.map { it.toLikeDetail() },
        published = this.published,
        searchable_text = this.searchableText,
        sections = this.sections.map { it.toSectionDetail() },
        tags =this.tags,
        title = this.title,
        updatedAt = this.updatedAt
    )
}


data class AuthorDetail(
    val id : String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String
)

fun AuthorDetailDto.toAuthorDetail() : AuthorDetail {
    return AuthorDetail(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}

data class LikeDetail(
    val userId: String
)

fun LikeDetailDto.toLikeDetail() : LikeDetail {
    return LikeDetail(
        userId = this.userId
    )
}

fun List<LikeDetail>.isLiked(userId: String) : Boolean{

    for (item in this){
        if (item.userId == userId){
            return true
        }
    }
    return false
}


data class SectionDetail(
    val blogId: String,
    val content: String,
    val id: String,
    val image: String,
    val title: String,
    val type: String
)

fun SectionDetailDto.toSectionDetail() : SectionDetail {
    return SectionDetail(
        blogId = this.blogId,
        id = this.id,
        content = this.content,
        image = this.image,
        title = this.title,
        type = this.type
    )
}




