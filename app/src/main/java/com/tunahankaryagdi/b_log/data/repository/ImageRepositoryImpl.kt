package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.image.PostImageResponse
import com.tunahankaryagdi.b_log.data.source.remote.ImageService
import com.tunahankaryagdi.b_log.domain.repository.ImageRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageService: ImageService): ImageRepository {
    override suspend fun postImage(file: File) : PostImageResponse{
        return imageService.postImage(MultipartBody.Part.createFormData("image",file.name,file.asRequestBody()))
    }
}