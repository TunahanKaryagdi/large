package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.comment.NewCommentRequest
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentResponse
import com.tunahankaryagdi.b_log.domain.repository.CommentRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostCommentUseCase @Inject constructor(private val commentRepository: CommentRepository){

    operator fun invoke(newCommentRequest: NewCommentRequest) : Flow<Resource<NewCommentResponse>>{

        return flow {
            try {
                val response = commentRepository.postComment(newCommentRequest)
                emit(Resource.Success(response))
            }
            catch (e:Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }

    }


}