package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentRequest
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentResponse

interface CommentRepository {

    suspend fun getCommentsByBlogId(blogId: String) : CommentResponse
    suspend fun postComment(newCommentRequest: NewCommentRequest): NewCommentResponse
}