package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.like.NewLikeResponse
import com.tunahankaryagdi.b_log.domain.repository.LikeRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostLikeUseCase @Inject constructor(private val likeRepository: LikeRepository) {

    operator fun invoke(blogId: String,userId: String) : Flow<Resource<NewLikeResponse>> {
        return flow {
            try {
                val response = likeRepository.postLike(blogId,userId)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }


        }
    }

}