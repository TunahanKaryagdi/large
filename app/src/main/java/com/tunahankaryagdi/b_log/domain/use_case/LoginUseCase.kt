package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.auth.LoginRequest
import com.tunahankaryagdi.b_log.data.model.auth.LoginResponse
import com.tunahankaryagdi.b_log.domain.repository.AuthRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val authRepository: AuthRepository){

    operator fun invoke(email: String, password: String) : Flow<Resource<LoginResponse>> {
        return flow {
            try {

                val response = authRepository.login(LoginRequest(email,password))
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }

}