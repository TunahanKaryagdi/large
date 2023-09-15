package com.tunahankaryagdi.b_log.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.b_log.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CommentsViewModel @Inject constructor( savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        savedStateHandle.get<String>(Constants.BLOG_ID)?.let { blogId->
            println(blogId)
        }
    }

}