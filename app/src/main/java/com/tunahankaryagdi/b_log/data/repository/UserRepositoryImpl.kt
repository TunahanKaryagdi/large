package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.user.SignupRequest
import com.tunahankaryagdi.b_log.data.model.user.SignupResponse
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserResponse
import com.tunahankaryagdi.b_log.data.model.user.UserResponse
import com.tunahankaryagdi.b_log.data.source.remote.UserService
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository {


    override suspend fun signup(signupRequest: SignupRequest): SignupResponse {
        return userService.signup(signupRequest)
    }

    override suspend fun getUserById(userId: String): UserResponse {
        return userService.getUserById(userId)
    }

    override suspend fun updateUserById(
        userId: String,
        updateUserRequest: UpdateUserRequest
    ): UpdateUserResponse {
        return userService.updateUserById(userId, updateUserRequest)
    }
}