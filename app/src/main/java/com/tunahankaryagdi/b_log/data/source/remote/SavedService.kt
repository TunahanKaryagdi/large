package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.saved.GetSavedByUserIdResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface SavedService {

    @POST("saved/save")
    suspend fun postSaved(@Body postSavedRequest: NewSavedRequest) : PostSavedResponse

    @GET("saved/{userId}")
    suspend fun getSavedByUserId(@Path("userId") userId: String) : GetSavedByUserIdResponse

}