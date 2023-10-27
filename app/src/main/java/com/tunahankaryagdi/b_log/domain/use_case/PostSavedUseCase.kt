package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.saved.NewSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse
import com.tunahankaryagdi.b_log.domain.repository.SavedRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostSavedUseCase @Inject constructor(private val savedRepository: SavedRepository)  {

    operator fun invoke(postSavedRequest: NewSavedBlogRequest) : Flow<Resource<PostSavedResponse>>{

        return flow {
            try {
                val response = savedRepository.postSaved(postSavedRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }

    }
}