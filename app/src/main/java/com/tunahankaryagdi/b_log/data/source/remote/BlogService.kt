package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.blog.BlogResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface BlogService{

    @GET("blogs")
    suspend fun getBlogs() : BlogResponse

    @GET("blogs/{id}")
    suspend fun getBlogById(@Path("id") id: String) : BlogDetailResponse

}