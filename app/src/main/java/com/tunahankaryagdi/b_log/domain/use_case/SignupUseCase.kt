package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.model.LoginResponse
import com.tunahankaryagdi.b_log.data.model.SignupRequest
import com.tunahankaryagdi.b_log.data.model.SignupResponse
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignupUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(name: String,surname: String,email: String,password: String) : Flow<Resource<SignupResponse>> {
        return flow {
            try {

                val response = userRepository.signup(SignupRequest(name,surname,email,password))
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}