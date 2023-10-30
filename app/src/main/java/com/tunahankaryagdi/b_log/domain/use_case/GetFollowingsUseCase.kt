package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.domain.model.comment.Comment
import com.tunahankaryagdi.b_log.domain.model.comment.toComment
import com.tunahankaryagdi.b_log.domain.model.follow.Following
import com.tunahankaryagdi.b_log.domain.model.follow.toFollowing
import com.tunahankaryagdi.b_log.domain.repository.FollowRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFollowingsUseCase @Inject constructor(private val followRepository: FollowRepository) {
    operator fun invoke(userId: String) : Flow<Resource<List<Following>>> {
        return flow {
            try {
                val response = followRepository.getFollowingsByUserId(userId)
                emit(Resource.Success(response.data.map { it.toFollowing() }))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}