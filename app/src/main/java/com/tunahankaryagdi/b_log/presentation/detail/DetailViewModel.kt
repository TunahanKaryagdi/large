package com.tunahankaryagdi.b_log.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.domain.model.BlogDetail
import com.tunahankaryagdi.b_log.domain.model.toBlogDetail
import com.tunahankaryagdi.b_log.domain.use_case.GetBlogByIdUseCase
import com.tunahankaryagdi.b_log.presentation.login.LoginUiState
import com.tunahankaryagdi.b_log.utils.Constants
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBlogByIdUseCase: GetBlogByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState = _uiState

    init {
        savedStateHandle.get<String>(Constants.BLOG_ID)?.let { blogId ->
            getBlogDetailById(blogId)
        }
    }


    fun getBlogDetailById(id: String){

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getBlogByIdUseCase.invoke(id).collect{resource->

                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, blogDetail = resource.data.blogDetail.toBlogDetail())
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)

                    }
                }
            }
        }
    }

}


data class DetailUiState(
    val isLoading: Boolean = false,
    val blogDetail: BlogDetail? = null,
    val error: String = ""
)