package com.tunahankaryagdi.b_log.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.domain.model.blog.BlogDetail
import com.tunahankaryagdi.b_log.domain.model.blog.isLiked
import com.tunahankaryagdi.b_log.domain.model.blog.toBlogDetail
import com.tunahankaryagdi.b_log.domain.use_case.DeleteLikeUseCase
import com.tunahankaryagdi.b_log.domain.use_case.GetBlogByIdUseCase
import com.tunahankaryagdi.b_log.domain.use_case.PostLikeUseCase
import com.tunahankaryagdi.b_log.utils.Constants
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBlogByIdUseCase: GetBlogByIdUseCase,
    private val postLikeUseCase: PostLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase,
    private val application: MyApplication,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState = _uiState

    init {
        savedStateHandle.get<String>(Constants.BLOG_ID)?.let { blogId ->
            getBlogDetailById(blogId)
        }

    }


    private fun getBlogDetailById(id: String){

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getBlogByIdUseCase.invoke(id).collect{resource->

                when(resource){
                    is Resource.Success->{
                        val blogDetail = resource.data
                        _uiState.value = _uiState.value.copy(isLoading = false, blogDetail = blogDetail)
                        isLiked(blogDetail)
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)

                    }
                }
            }
        }
    }


    fun onClickLike(blogDetail: BlogDetail){
        viewModelScope.launch {
            postLikeUseCase.invoke(blogDetail.id,application.getUserId()).collect{resource->
                when(resource){

                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLiked = true)
                    }
                    is Resource.Error->{
                        println("değil")
                    }
                }
            }
        }
    }

    fun onClickUnlike(blogDetail: BlogDetail){

            viewModelScope.launch {
                deleteLikeUseCase.invoke(blogDetail.id,application.getUserId()).collect{resource->

                    when(resource){

                        is Resource.Success->{
                            _uiState.value = _uiState.value.copy(isLiked = false)
                        }
                        is Resource.Error->{
                            println("değil")
                        }
                    }
                }
            }
    }


    private fun isLiked(blogDetail: BlogDetail){
        val isLiked =  blogDetail.likes.isLiked(application.getUserId())
        _uiState.value = _uiState.value.copy(isLiked = isLiked)
    }

}


data class DetailUiState(
    val isLoading: Boolean = false,
    val isLiked: Boolean = false,
    val blogDetail: BlogDetail? = null,
    val error: String = ""
)