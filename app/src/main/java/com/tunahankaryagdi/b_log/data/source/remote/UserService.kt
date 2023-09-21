package com.tunahankaryagdi.b_log.data.source.remote

import com.tunahankaryagdi.b_log.data.model.user.SignupRequest
import com.tunahankaryagdi.b_log.data.model.user.SignupResponse
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserResponse
import com.tunahankaryagdi.b_log.data.model.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


interface UserService{

    @POST("users")
    suspend fun signup(@Body signupRequest: SignupRequest) : SignupResponse

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String) : UserResponse


    @PATCH("users/{userId}")
    suspend fun updateUserById(@Path("userId") userId: String, @Body updateUserRequest: UpdateUserRequest) : UpdateUserResponse


}