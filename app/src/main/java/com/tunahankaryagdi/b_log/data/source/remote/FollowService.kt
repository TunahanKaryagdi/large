package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.follow.DeleteFollowResponse
import com.tunahankaryagdi.b_log.data.model.follow.FollowingResponse
import com.tunahankaryagdi.b_log.data.model.follow.NewFollowResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FollowService {

    @POST("follows/{userId}")
    suspend fun postFollow(@Header("Authorization") token: String, @Path("userId") userId :String) : NewFollowResponse

    @DELETE("follows/{userId}")
    suspend fun deleteFollow(@Header("Authorization") token: String, @Path("userId") userId :String) : DeleteFollowResponse

    @GET("follows/followings/{userId}")
    suspend fun getFollowingsByUserId(@Path("userId") userId: String) : FollowingResponse


}