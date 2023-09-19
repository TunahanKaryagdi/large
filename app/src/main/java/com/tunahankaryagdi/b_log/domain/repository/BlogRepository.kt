package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.blog.BlogResponse

interface BlogRepository {

    suspend fun getBlogs() : BlogResponse

    suspend fun getBlogById(id: String) : BlogDetailResponse
}