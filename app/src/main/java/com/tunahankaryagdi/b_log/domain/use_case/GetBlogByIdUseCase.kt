package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.BlogDetailResponse
import com.tunahankaryagdi.b_log.data.model.BlogResponse
import com.tunahankaryagdi.b_log.domain.repository.BlogRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogByIdUseCase @Inject constructor(private val blogRepository: BlogRepository) {


    operator fun invoke(id: String) : Flow<Resource<BlogDetailResponse>> {
        return flow {
            try {

                val response = blogRepository.getBlogById(id)
                emit(Resource.Success(response))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}