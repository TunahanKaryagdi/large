package com.tunahankaryagdi.b_log.domain.use_case

import com.tunahankaryagdi.b_log.data.model.image.PostImageResponse
import com.tunahankaryagdi.b_log.domain.repository.ImageRepository
import com.tunahankaryagdi.b_log.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class PostImageUseCase @Inject constructor(private val imageRepository: ImageRepository) {

    operator fun invoke(file: File) : Flow<Resource<PostImageResponse>>{

        return flow {
            try {
                val response = imageRepository.postImage(file)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }


        }


    }


}