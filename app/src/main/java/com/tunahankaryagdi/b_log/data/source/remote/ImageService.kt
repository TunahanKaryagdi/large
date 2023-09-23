package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.image.PostImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageService {

    @Multipart
    @POST("upload/image")
    suspend fun postImage(@Part image: MultipartBody.Part) : PostImageResponse

}