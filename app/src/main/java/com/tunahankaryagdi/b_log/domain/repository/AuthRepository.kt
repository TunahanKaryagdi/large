package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.model.LoginResponse

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest) : LoginResponse
}