package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.blog.NewBlogRequest
import com.tunahankaryagdi.b_log.data.model.blog.NewBlogResponse
import com.tunahankaryagdi.b_log.domain.repository.BlogRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostBlogUseCase @Inject constructor(private val blogRepository: BlogRepository) {

    operator fun invoke(newBlogRequest: NewBlogRequest) : Flow<Resource<NewBlogResponse>>{

        return flow {

            try {
                val response = blogRepository.postBlog(newBlogRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }

        }

    }

}