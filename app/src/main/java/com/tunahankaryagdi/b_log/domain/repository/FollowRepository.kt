package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.follow.DeleteFollowResponse
import com.tunahankaryagdi.b_log.data.model.follow.FollowingResponse
import com.tunahankaryagdi.b_log.data.model.follow.NewFollowResponse

interface FollowRepository {

    suspend fun postFollow(token: String,userId: String) : NewFollowResponse

    suspend fun deleteFollow(token: String,userId: String) : DeleteFollowResponse

    suspend fun getFollowingsByUserId(userId : String) : FollowingResponse
}