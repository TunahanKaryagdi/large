package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.like.DeleteLikeResponse
import com.tunahankaryagdi.b_log.data.model.like.NewLikeResponse
import com.tunahankaryagdi.b_log.data.source.remote.LikeService
import com.tunahankaryagdi.b_log.domain.repository.LikeRepository
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(private val likeService: LikeService): LikeRepository {
    override suspend fun postLike(blogId: String, userId: String) : NewLikeResponse{
        return likeService.postLike(blogId,userId)
    }

    override suspend fun deleteLike(blogId: String, userId: String) : DeleteLikeResponse{
        return likeService.deleteLike(blogId,userId)
    }
}