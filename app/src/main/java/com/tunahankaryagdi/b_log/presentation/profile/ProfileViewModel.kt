package com.tunahankaryagdi.b_log.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authDataStore: AuthDataStore) : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())
    val uiState = _uiState



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
            authDataStore.saveTokens("","")
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
    val showBottomSheet :Boolean = false,
    val showLogoutDialog: Boolean = false,
    val selectedTabIndex: Int = 0,
    val navigateToLogin : Boolean = false
)