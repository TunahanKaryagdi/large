package com.tunahankaryagdi.b_log.domain.repository

import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.SavedBlogResponse
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.PostSavedResponse

interface SavedRepository {
    suspend fun postSaved(postSavedRequest: NewSavedBlogRequest) : PostSavedResponse
    suspend fun getSavedByUserId(userId: String) : SavedBlogResponse
    suspend fun deleteSaved(deleteSavedBlogRequest: DeleteSavedBlogRequest) : DeleteSavedBlogResponse

}