package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.like.DeleteLikeResponse
import com.tunahankaryagdi.b_log.data.model.like.NewLikeResponse
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeService {

    @POST("likes/{blogId}&{userId}")
    suspend fun postLike(@Path("blogId") blogId: String,@Path("userId") userId: String) : NewLikeResponse

    @DELETE("likes/{blogId}&{userId}")
    suspend fun deleteLike(@Path("blogId") blogId: String,@Path("userId") userId: String) : DeleteLikeResponse


}