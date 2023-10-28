package com.tunahankaryagdi.b_log.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.comment.NewCommentRequest
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.comment.Comment
import com.tunahankaryagdi.b_log.domain.model.comment.toComment
import com.tunahankaryagdi.b_log.domain.use_case.GetCommentsByBlogIdUseCase
import com.tunahankaryagdi.b_log.domain.use_case.PostCommentUseCase
import com.tunahankaryagdi.b_log.utils.Constants
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentsByBlogIdUseCase: GetCommentsByBlogIdUseCase,
    private val postCommentUseCase: PostCommentUseCase,
    private val application: MyApplication,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<CommentUiState> = MutableStateFlow(CommentUiState())
    val uiState = _uiState



    init {
        savedStateHandle.get<String>(Constants.BLOG_ID)?.let { blogId->
            getCommentsByBlogId(blogId)
        }
    }

    private fun getCommentsByBlogId(blogId: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getCommentsByBlogIdUseCase.invoke(blogId).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, comments = resource.data)
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)
                    }
                }

            }
        }
    }

    private fun postComment(){

        val blogId = savedStateHandle.get<String>(Constants.BLOG_ID) ?: ""
        val newComment = NewCommentRequest(blogId, _uiState.value.newComment,application.getUserId())

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            postCommentUseCase.invoke(newComment).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, newComment = "")
                        getCommentsByBlogId(blogId)
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

    fun onClickPostComment(){
        if (_uiState.value.newComment.isNotBlank()){
            postComment()
        }
    }


}

data class CommentUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val comments: List<Comment>? = null,
    val newComment: String = "",
)