package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.image.PostImageResponse
import java.io.File

interface ImageRepository {

    suspend fun postImage(file: File) : PostImageResponse
}