package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.model.LoginResponse
import com.tunahankaryagdi.b_log.data.source.remote.AuthService
import com.tunahankaryagdi.b_log.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return authService.login(loginRequest)
    }

}