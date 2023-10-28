package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.comment.CommentResponse
import com.tunahankaryagdi.b_log.data.model.user.UserResponse
import com.tunahankaryagdi.b_log.domain.model.user.User
import com.tunahankaryagdi.b_log.domain.model.user.toUser
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(userId: String): Flow<Resource<User>>{

        return flow {
            try {

                val response = userRepository.getUserById(userId)
                emit(Resource.Success(response.user.toUser()))
            }
            catch (e: Exception){

                emit(Resource.Error(e.message ?: ""))
            }
        }
    }


}