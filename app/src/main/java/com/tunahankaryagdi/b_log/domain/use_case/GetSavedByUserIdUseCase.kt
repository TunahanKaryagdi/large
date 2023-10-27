package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.domain.model.saved.Saved
import com.tunahankaryagdi.b_log.domain.model.saved.toSaved
import com.tunahankaryagdi.b_log.domain.repository.SavedRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetSavedByUserIdUseCase @Inject constructor(private val savedRepository: SavedRepository) {

    operator fun invoke(userId : String) : Flow<Resource<List<Saved>>>{

        return flow {
            try {
                val response = savedRepository.getSavedByUserId(userId)
                val x = mutableListOf<Saved>()
                response.data.map {
                    x.add(it.savedBlogDto.toSaved())
                }
                emit(Resource.Success(x))

            }
            catch (e : Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}