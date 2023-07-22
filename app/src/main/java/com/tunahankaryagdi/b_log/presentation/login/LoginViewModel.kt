package com.tunahankaryagdi.b_log.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.repository.AuthRepository
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val navigateToMain: Boolean = false
)


@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

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

            val result = authRepository.login(LoginRequest(_uiState.value.email,_uiState.value.password))

            when(result){
                is Resource.Success->{
                    println(result.data.data.accessToken)
                    //datastore kayıt
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        navigateToMain = true
                    )
                }
                is Resource.Error->{
                    println(result.message)
                }
            }

        }

    }


}