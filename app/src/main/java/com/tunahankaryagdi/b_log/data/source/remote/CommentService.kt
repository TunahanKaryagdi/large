package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {


  @GET("comments/blog/{blogId}")
  suspend fun getCommentsByBlogId(@Path("blogId") blogId: String) : CommentResponse

}