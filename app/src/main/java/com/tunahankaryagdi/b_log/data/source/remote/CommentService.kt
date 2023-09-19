package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.comment.NewCommentRequest
import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {


  @GET("comments/blog/{blogId}")
  suspend fun getCommentsByBlogId(@Path("blogId") blogId: String) : CommentResponse

  @POST("comments")
  suspend fun postComment(@Body commentRequest : NewCommentRequest) : NewCommentResponse
}