package com.tunahankaryagdi.b_log.data.repository

import com.tunahankaryagdi.b_log.data.model.saved.GetSavedByUserIdResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse
import com.tunahankaryagdi.b_log.data.source.remote.SavedService
import com.tunahankaryagdi.b_log.domain.repository.SavedRepository
import javax.inject.Inject

class SavedRepositoryImpl @Inject constructor(private val savedService: SavedService) : SavedRepository {
    override suspend fun postSaved(postSavedRequest: NewSavedRequest): PostSavedResponse {
        return savedService.postSaved(postSavedRequest)
    }

    override suspend fun getSavedByUserId(userId: String): GetSavedByUserIdResponse {
        return savedService.getSavedByUserId(userId)
    }
}