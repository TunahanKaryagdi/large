package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.blog.BlogResponse
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogRequest
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface BlogService{

    @GET("blogs")
    suspend fun getBlogs() : BlogResponse

    @GET("blogs/{id}")
    suspend fun getBlogById(@Path("id") id: String) : BlogDetailResponse

    @GET("blogs/author/{id}")
    suspend fun getBlogsByUserId(@Path("id") userId: String) : BlogResponse

    @POST("blogs")
    suspend fun postBlog(@Body newBlogRequest: NewBlogRequest) : NewBlogResponse

}