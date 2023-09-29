package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.like.DeleteLikeResponse
import com.tunahankaryagdi.b_log.data.model.like.NewLikeResponse

interface LikeRepository {


    suspend fun postLike(blogId: String,userId: String) : NewLikeResponse

    suspend fun deleteLike(blogId: String,userId: String) : DeleteLikeResponse

}