package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse

interface CommentRepository {

    suspend fun getCommentsByBlogId(blogId: String) : CommentResponse
}