package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.follow.NewFollowResponse
import com.tunahankaryagdi.b_log.domain.repository.FollowRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PostFollowUseCase @Inject constructor(private val followRepository: FollowRepository){

    operator fun invoke(token :String, userId: String) : Flow<Resource<NewFollowResponse>>{

        return flow {
            try {
                val response = followRepository.postFollow("Bearer $token", userId)
                emit(Resource.Success(response))
            }
            catch (e :Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}