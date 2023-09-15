package com.tunahankaryagdi.b_log.domain.model

import com.tunahankaryagdi.b_log.data.model.BlogDto


data class BlogDetail(
    val id: String,
    val author: Author,
    val createdAt : String,
    val image: String,
    val likes: List<Like>,
    val published: Boolean,
    val searchable_text: String,
    val sections: List<Section>,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)



fun BlogDto.toBlogDetail() : BlogDetail{
    return BlogDetail(
        id = this.id,
        author = this.author.toAuthor(),
        createdAt = this.createdAt,
        image = this.image,
        likes = this.likes.map { it.toLike() },
        published = this.published,
        searchable_text = this.searchable_text,
        sections = this.sections.map { it.toSection() },
        tags =this.tags,
        title = this.title,
        updatedAt = this.updatedAt
    )
}