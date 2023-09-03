package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.SignupRequest
import com.tunahankaryagdi.b_log.data.model.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface UserService{

    @POST("users")
    suspend fun signup(@Body signupRequest: SignupRequest) : SignupResponse


}