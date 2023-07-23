package com.tunahankaryagdi.b_log.data.model



data class Blog(
    val authorId: String,
    val createdAt: String,
    val id: String,
    val image: String,
    val published: Boolean,
    val sections: List<Section>,
    val tags: List<String>,
    val title: String,
    val updatedAt: String
)

data class Section(
    val content: String,
    val id: String,
    val image: String,
    val title: String
)
