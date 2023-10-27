package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.saved.GetSavedByUserIdResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse

interface SavedRepository {
    suspend fun postSaved(postSavedRequest: NewSavedRequest) : PostSavedResponse
    suspend fun getSavedByUserId(userId: String) : GetSavedByUserIdResponse
}