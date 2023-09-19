package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.auth.LoginRequest
import com.tunahankaryagdi.b_log.data.model.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


}