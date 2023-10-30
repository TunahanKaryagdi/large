package com.tunahankaryagdi.b_log.domain.model.follow

import com.tunahankaryagdi.b_log.data.model.follow.FollowingResponse
import com.tunahankaryagdi.b_log.data.model.follow.FollowingResponseData

data class Following(
    val followerId: String,
    val followingId: String
)


fun FollowingResponseData.toFollowing() : Following{
    return Following(
        followerId = this.followerId,
        followingId = this.followingId
    )
}


