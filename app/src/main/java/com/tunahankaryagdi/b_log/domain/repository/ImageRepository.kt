package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.image.NewImageResponse
import java.io.File

interface ImageRepository {

    suspend fun postImage(file: File) : NewImageResponse
}