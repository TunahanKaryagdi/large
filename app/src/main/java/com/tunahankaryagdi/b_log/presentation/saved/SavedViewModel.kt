package com.tunahankaryagdi.b_log.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.saved.SavedBlog
import com.tunahankaryagdi.b_log.domain.use_case.DeleteSavedUseCase
import com.tunahankaryagdi.b_log.domain.use_case.GetSavedByUserIdUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getSavedByUserIdUseCase: GetSavedByUserIdUseCase,
    private val deleteSavedUseCase: DeleteSavedUseCase,
    private val application: MyApplication
) : ViewModel() {

    private val _uiState : MutableStateFlow<SavedUiState> = MutableStateFlow(SavedUiState())
    val uiState = _uiState

    init {
        getSavedBlogs()
    }

    private fun getSavedBlogs(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getSavedByUserIdUseCase.invoke(application.getUserId()).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, savedBlogs = resource.data)
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)
                    }
                }
            }
        }
    }

    fun onClickUnsaved(savedBlog: SavedBlog){
        viewModelScope.launch {
            deleteSavedUseCase(DeleteSavedBlogRequest(savedBlog.id,application.getUserId())).collect{resource->
                when(resource){
                    is Resource.Success->{

                    }
                    is Resource.Error->{

                    }
                }
            }
        }
    }


}

data class SavedUiState(
    val isLoading : Boolean = false,
    val savedBlogs : List<SavedBlog> = emptyList(),
    val error : String = ""
)