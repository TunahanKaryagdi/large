package com.tunahankaryagdi.b_log.data.model.blog

data class NewBlogResponse(
    val data: Data,
    val message: String
)


data class Data(
    val blogId: String
)