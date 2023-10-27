package com.tunahankaryagdi.b_log.domain.model.saved

import com.tunahankaryagdi.b_log.data.model.saved.SavedBlogDto

data class SavedBlog(
    val authorId: String,
    val id: String,
    val image: String,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)


fun SavedBlogDto.toSaved() : SavedBlog{

    return SavedBlog(
        authorId = this.authorId,
        id = this.id,
        image =this.image,
        tags = this.tags,
        title = this.title,
        updatedAt = this.updatedAt,
    )
}
