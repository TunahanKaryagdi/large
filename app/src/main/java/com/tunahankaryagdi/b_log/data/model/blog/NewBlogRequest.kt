package com.tunahankaryagdi.b_log.data.model.blog



data class NewBlogRequest(
    val title: String,
    val image: String,
    val published: Boolean,
    val authorId: String,
    val tags: List<String>,
    val sections: List<NewBlogSection>
)

data class NewBlogSection(
    val title: String,
    val content: String,
    val image: String,
    val type: String
)