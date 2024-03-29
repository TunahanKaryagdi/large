package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.blog.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.blog.BlogResponse
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogRequest
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogResponse
import com.tunahankaryagdi.b_log.data.source.remote.BlogService
import com.tunahankaryagdi.b_log.domain.repository.BlogRepository
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(private val blogService: BlogService) :
    BlogRepository {
    override suspend fun getBlogs(): BlogResponse {
        return blogService.getBlogs()
    }

    override suspend fun getBlogById(id: String): BlogDetailResponse {
        return blogService.getBlogById(id)
    }

    override suspend fun getBlogByUserId(userId: String): BlogResponse {
        return blogService.getBlogsByUserId(userId)
    }

    override suspend fun postBlog(newBlogRequest: NewBlogRequest): NewBlogResponse {
        return blogService.postBlog(newBlogRequest)
    }


}