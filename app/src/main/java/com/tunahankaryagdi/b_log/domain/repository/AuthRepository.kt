package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.auth.LoginRequest
import com.tunahankaryagdi.b_log.data.model.auth.LoginResponse

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest) : LoginResponse
}