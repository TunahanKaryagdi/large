package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogResponse
import com.tunahankaryagdi.b_log.domain.repository.SavedRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteSavedUseCase @Inject constructor(private val savedRepository: SavedRepository) {

    operator fun invoke(deleteSavedBlogRequest: DeleteSavedBlogRequest) : Flow<Resource<DeleteSavedBlogResponse>>{
        return flow {
            try {
                val response = savedRepository.deleteSaved(deleteSavedBlogRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}