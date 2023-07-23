package com.tunahankaryagdi.b_log.data.model

data class BlogResponse(
    val data: List<Blog>,
    val message: String,
    val status: Int
)