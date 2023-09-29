package com.tunahankaryagdi.b_log.data.model.like


data class NewLikeResponse(
    val data : NewLike,
    val message: String
)

data class NewLike(
    val likeId: String
)