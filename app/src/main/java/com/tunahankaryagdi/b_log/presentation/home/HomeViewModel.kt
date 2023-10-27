package com.tunahankaryagdi.b_log.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.saved.DeleteSavedBlogRequest
import com.tunahankaryagdi.b_log.data.model.saved.NewSavedBlogRequest
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.domain.model.blog.toBlog
import com.tunahankaryagdi.b_log.domain.model.saved.SavedBlog
import com.tunahankaryagdi.b_log.domain.use_case.DeleteSavedUseCase
import com.tunahankaryagdi.b_log.domain.use_case.GetBlogsUseCase
import com.tunahankaryagdi.b_log.domain.use_case.GetSavedByUserIdUseCase
import com.tunahankaryagdi.b_log.domain.use_case.PostSavedUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlogsUseCase,
    private val getSavedByUserIdUseCase: GetSavedByUserIdUseCase,
    private val postSavedUseCase: PostSavedUseCase,
    private val deleteSavedUseCase: DeleteSavedUseCase,
    private val application: MyApplication
): ViewModel(){

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState

    private var _savedBlogs : List<SavedBlog> = listOf()

    init {
        getSavedBlogs()
        getBlogs()

    }


     fun getBlogs(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getBlogsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(isLoading = false, blogs = resource.data.blogs.map { it.toBlog() })
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)
                    }
                }
            }
        }
    }

     fun getSavedBlogs(){
        viewModelScope.launch {
            getSavedByUserIdUseCase.invoke(application.getUserId()).collect{resource->
                when(resource){
                    is Resource.Error->{
                        application.getUserId()
                        _uiState.value = _uiState.value.copy(error = resource.message)
                    }
                    is Resource.Success->{
                        _savedBlogs = resource.data
                    }
                }
            }
        }
    }


    fun isSaved(blog: Blog) : Boolean{

        for (item in _savedBlogs){
            if (item.id == blog.id) return true
        }
        return false
    }

    fun onClickSaved(blog: Blog){
        viewModelScope.launch {
            postSavedUseCase.invoke(NewSavedBlogRequest(blog.id,application.getUserId())).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(needRefresh = true)
                    }
                    is Resource.Error->{

                    }
                }
            }
        }
    }

    fun onClickUnsaved(blog: Blog){
        viewModelScope.launch {
            deleteSavedUseCase.invoke(DeleteSavedBlogRequest(blog.id,application.getUserId())).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(needRefresh = true)
                    }
                    is Resource.Error->{
                        println("error")
                    }
                }
            }
        }
    }


}


data class HomeUiState(
    val isLoading: Boolean = false,
    val blogs: List<Blog> = emptyList(),
    val error: String = "",
    val needRefresh :Boolean = false,
)