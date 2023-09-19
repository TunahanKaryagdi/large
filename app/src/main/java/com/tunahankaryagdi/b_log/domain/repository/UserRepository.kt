package com.tunahankaryagdi.b_log.domain.repository


import com.tunahankaryagdi.b_log.data.model.user.SignupRequest
import com.tunahankaryagdi.b_log.data.model.user.SignupResponse
import com.tunahankaryagdi.b_log.data.model.user.UserResponse

interface UserRepository {

    suspend fun signup(signupRequest: SignupRequest) : SignupResponse

    suspend fun getUserById(userId: String) : UserResponse
}