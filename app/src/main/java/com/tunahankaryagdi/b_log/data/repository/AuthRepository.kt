package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.model.LoginResponse
import com.tunahankaryagdi.b_log.data.source.remote.AuthService
import com.tunahankaryagdi.b_log.utils.Resource
import javax.inject.Inject


class AuthRepository @Inject constructor(private val authService: AuthService) {


    suspend fun login(loginRequest: LoginRequest) :Resource<LoginResponse> {

       return try {
            Resource.Success(authService.login(loginRequest))
        }
        catch (e: Exception){
            Resource.Error(e.message ?: "unexpected error")
        }
    }


}