package com.tunahankaryagdi.b_log.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.BlogDto
import com.tunahankaryagdi.b_log.domain.model.Blog
import com.tunahankaryagdi.b_log.domain.model.toBlog
import com.tunahankaryagdi.b_log.domain.use_case.GetBlogsUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getBlogsUseCase: GetBlogsUseCase): ViewModel(){

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState


    init {
        getBlogs()
    }


    private fun getBlogs(){

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getBlogsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(isLoading = false, blogs = resource.data.blogs.map { it.toBlog() })
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                }
            }
        }
    }




}


data class HomeUiState(
    val isLoading: Boolean = false,
    val blogs: List<Blog> = emptyList(),
    val navigateToDetail: Boolean = false
)