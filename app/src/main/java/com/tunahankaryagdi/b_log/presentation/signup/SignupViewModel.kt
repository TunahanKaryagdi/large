package com.tunahankaryagdi.b_log.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.domain.use_case.SignupUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val signupUseCase: SignupUseCase) : ViewModel() {

    private val _uiState : MutableStateFlow<SignupUiState> = MutableStateFlow(SignupUiState())
    val uiState = _uiState


    fun signup(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            signupUseCase.invoke(_uiState.value.name,_uiState.value.surname,_uiState.value.email,_uiState.value.password).collect{resource->

                when(resource){
                    is Resource.Success ->{
                        _uiState.value = _uiState.value.copy(isLoading = false, navigateToLogin = true, message = resource.data.message)
                    }
                    is Resource.Error ->{
                        _uiState.value = _uiState.value.copy(isLoading = false, message = resource.message)
                    }
                }
            }
        }
    }


    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name
        )
    }

    fun onSurnameChange(surname: String){
        _uiState.value = _uiState.value.copy(
            surname = surname
        )
    }

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

}

data class SignupUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val navigateToLogin : Boolean = false,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = ""
)