package com.tunahankaryagdi.b_log.data.model.follow

data class FollowingResponse(
    val data : List<FollowingResponseData>,
    val message: String,
    val status: Int
)

data class FollowingResponseData(
    val followerId: String,
    val followingId: String
)