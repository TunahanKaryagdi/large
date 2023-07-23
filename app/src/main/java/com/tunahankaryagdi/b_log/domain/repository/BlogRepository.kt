package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.Blog
import com.tunahankaryagdi.b_log.data.model.BlogResponse

interface BlogRepository {

    suspend fun getBlogs() : BlogResponse
}