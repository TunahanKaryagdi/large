package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.follow.NewFollowResponse

interface FollowRepository {

    suspend fun postFollow(token: String,userId: String) : NewFollowResponse
}