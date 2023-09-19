package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentRequest
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentResponse
import com.tunahankaryagdi.b_log.data.source.remote.CommentService
import com.tunahankaryagdi.b_log.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentService: CommentService) : CommentRepository {

    override suspend fun getCommentsByBlogId(blogId: String): CommentResponse {
        return commentService.getCommentsByBlogId(blogId)
    }

    override suspend fun postComment(newCommentRequest: NewCommentRequest): NewCommentResponse {
        return commentService.postComment(newCommentRequest)
    }
}