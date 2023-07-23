package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.BlogResponse
import retrofit2.http.GET


interface BlogService{

    @GET("blogs")
    suspend fun getBlogs() : BlogResponse
}