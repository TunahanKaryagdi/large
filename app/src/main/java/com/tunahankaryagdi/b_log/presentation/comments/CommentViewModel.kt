package com.tunahankaryagdi.b_log.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.domain.model.comment.Comment
import com.tunahankaryagdi.b_log.domain.model.comment.toComment
import com.tunahankaryagdi.b_log.domain.use_case.GetCommentsByBlogIdUseCase
import com.tunahankaryagdi.b_log.utils.Constants
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentsByBlogIdUseCase: GetCommentsByBlogIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<CommentUiState> = MutableStateFlow(CommentUiState())
    val uiState = _uiState



    init {
        savedStateHandle.get<String>(Constants.BLOG_ID)?.let { blogId->
            getCommentsByBlogId(blogId)
        }
    }

    fun getCommentsByBlogId(blogId: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getCommentsByBlogIdUseCase.invoke(blogId).collect{resource->

                when(resource){

                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, comments = resource.data.comments.map { it.toComment() })
                    }

                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)
                    }
                }


            }
        }
    }

    fun onCommentChange(value: String){
        _uiState.value = _uiState.value.copy(newComment = value)

    }


}

data class CommentUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val comments: List<Comment>? = null,
    val newComment: String = "",
)