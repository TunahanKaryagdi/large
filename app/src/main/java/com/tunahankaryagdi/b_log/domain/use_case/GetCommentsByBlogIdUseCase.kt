package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.CommentResponse
import com.tunahankaryagdi.b_log.domain.repository.CommentRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsByBlogIdUseCase @Inject constructor(private val commentRepository: CommentRepository) {

    operator fun invoke(blogId: String) : Flow<Resource<CommentResponse>> {
        return flow {
            try {

                val response = commentRepository.getCommentsByBlogId(blogId)
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }


}