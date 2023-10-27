package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.SavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface SavedService {

    @POST("saved/save")
    suspend fun postSaved(@Body postSavedRequest: NewSavedBlogRequest) : PostSavedResponse
    @GET("saved/{userId}")
    suspend fun getSavedByUserId(@Path("userId") userId: String) : SavedBlogResponse
    @DELETE("saved/save")
    suspend fun deleteSaved(@Body deleteSavedBlogRequest: DeleteSavedBlogRequest) : DeleteSavedBlogResponse


}