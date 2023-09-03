package com.tunahankaryagdi.b_log.domain.repository


import com.tunahankaryagdi.b_log.data.model.SignupRequest
import com.tunahankaryagdi.b_log.data.model.SignupResponse

interface UserRepository {

    suspend fun signup(signupRequest: SignupRequest) : SignupResponse
}