package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.follow.NewFollowResponse
import com.tunahankaryagdi.b_log.data.source.remote.FollowService
import com.tunahankaryagdi.b_log.domain.repository.FollowRepository
import javax.inject.Inject

class FollowRepositoryImpl @Inject constructor(private val followService: FollowService) : FollowRepository{
    override suspend fun postFollow(token: String, userId: String) : NewFollowResponse {
        return followService.postFollow(token, userId)
    }

}