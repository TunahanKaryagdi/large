package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.SavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse
import com.tunahankaryagdi.b_log.data.source.remote.SavedService
import com.tunahankaryagdi.b_log.domain.repository.SavedRepository
import javax.inject.Inject

class SavedRepositoryImpl @Inject constructor(private val savedService: SavedService) : SavedRepository {
    override suspend fun postSaved(postSavedRequest: NewSavedBlogRequest): PostSavedResponse {
        return savedService.postSaved(postSavedRequest)
    }

    override suspend fun getSavedByUserId(userId: String): SavedBlogResponse {
        return savedService.getSavedByUserId(userId)
    }

    override suspend fun deleteSaved(deleteSavedBlogRequest: DeleteSavedBlogRequest): DeleteSavedBlogResponse {
        return savedService.deleteSaved(deleteSavedBlogRequest)
    }
}