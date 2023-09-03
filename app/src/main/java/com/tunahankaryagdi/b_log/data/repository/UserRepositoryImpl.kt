package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.SignupRequest
import com.tunahankaryagdi.b_log.data.model.SignupResponse
import com.tunahankaryagdi.b_log.data.source.remote.UserService
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository {


    override suspend fun signup(signupRequest: SignupRequest): SignupResponse {
        return userService.signup(signupRequest)
    }
}