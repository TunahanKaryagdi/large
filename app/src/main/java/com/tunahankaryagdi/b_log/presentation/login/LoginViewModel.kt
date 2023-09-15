package com.tunahankaryagdi.b_log.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.domain.use_case.LoginUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase,private val authDataStore: AuthDataStore) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState = _uiState


    fun onEmailChange(email: String){
        _uiState.value = _uiState.value.copy(
            email = email
        )
    }

    fun onPasswordChange(password: String){
        _uiState.value = _uiState.value.copy(
            password = password
        )
    }

    fun login(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            loginUseCase.invoke(_uiState.value.email,_uiState.value.password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val tokens = resource.data.data
                        authDataStore.saveTokens(tokens ?.accessToken ?: "",tokens?.refreshToken ?: "")
                        _uiState.value = _uiState.value.copy(isLoading = false, navigateToMain = true, message = resource.data.message)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false, message = resource.message)

                    }
                }
            }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val navigateToMain: Boolean = false,
    val message: String = ""
)