package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.blog.BlogResponse
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogRequest
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogResponse

interface BlogRepository {

    suspend fun getBlogs() : BlogResponse

    suspend fun getBlogById(id: String) : BlogDetailResponse

    suspend fun getBlogByUserId(userId: String) : BlogResponse

    suspend fun postBlog(newBlogRequest: NewBlogRequest) : NewBlogResponse
}