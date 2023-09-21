package com.tunahankaryagdi.b_log.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.blog.Blog
import com.tunahankaryagdi.b_log.domain.model.blog.toBlog
import com.tunahankaryagdi.b_log.domain.model.user.toUser
import com.tunahankaryagdi.b_log.domain.model.user.User
import com.tunahankaryagdi.b_log.domain.use_case.GetBlogsByUserIdUseCase
import com.tunahankaryagdi.b_log.domain.use_case.GetUserByIdUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val application: MyApplication,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getBlogsByUserIdUseCase: GetBlogsByUserIdUseCase
    ) : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())
    val uiState = _uiState


    init {
        getUserProfile(application.getUserId())

    }



    private fun getUserProfile(id: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getUserByIdUseCase.invoke(id).collect{resource->

                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, user = resource.data.user.toUser())
                        getBlogsByUserId(application.getUserId())
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)

                    }
                }
            }
        }
    }

    private fun getBlogsByUserId(userId: String){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(usersBlogLoading = true)
            getBlogsByUserIdUseCase.invoke(userId).collect{resource->

                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(usersBlogLoading = false, usersBlog = resource.data.blogs.map { it.toBlog() })
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(usersBlogLoading = false, usersBlogError = resource.message)

                    }
                }
            }
        }
    }


    fun onClickLogout(){
        _uiState.value = _uiState.value.copy(showLogoutDialog = true)

    }

    fun onClickSettings(){
        _uiState.value = _uiState.value.copy(showBottomSheet = true)
    }


    fun onClickTab(selectedIndex: Int){
        _uiState.value = _uiState.value.copy(selectedTabIndex = selectedIndex)

    }

    fun onClickConfirmLogout(){
        viewModelScope.launch {
            authDataStore.saveTokens("","",)
            application.setUserId("")
            _uiState.value = _uiState.value.copy(navigateToLogin = true)
        }
    }

    fun onClickCancelLogout(){
        _uiState.value = _uiState.value.copy(showLogoutDialog = false, showBottomSheet = false)
    }

    fun onDismissBottomSheet(){
        _uiState.value = _uiState.value.copy(showBottomSheet = false)
    }
}


data class ProfileUiState(
    val isLoading: Boolean = false,
    val usersBlogLoading: Boolean = false,
    val error: String = "",
    val usersBlogError: String = "",
    val user: User? = null,
    val usersBlog : List<Blog>? = null,
    val showBottomSheet: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val selectedTabIndex: Int = 0,
    val navigateToLogin: Boolean = false
)