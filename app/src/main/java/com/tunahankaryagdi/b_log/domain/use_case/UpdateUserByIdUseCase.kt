package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserResponse
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserByIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(userId: String, updateUserRequest: UpdateUserRequest) : Flow<Resource<UpdateUserResponse>>{

        return flow {
            try {
                val response = userRepository.updateUserById(userId,updateUserRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }

    }

}