package com.tunahankaryagdi.b_log.presentation.edit_profile

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.domain.model.user.User
import com.tunahankaryagdi.b_log.domain.model.user.toUser
import com.tunahankaryagdi.b_log.domain.use_case.GetUserByIdUseCase
import com.tunahankaryagdi.b_log.domain.use_case.UpdateUserByIdUseCase
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val application: MyApplication,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase
) : ViewModel(){

    private val _uiState :MutableStateFlow<EditProfileUiState> = MutableStateFlow(EditProfileUiState())
    val uiState = _uiState

    init {
        getUserById(application.getUserId())
    }

    private fun getUserById(id: String){

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getUserByIdUseCase.invoke(id).collect{resource->
                when(resource){
                    is Resource.Success->{
                        _uiState.value = _uiState.value.copy(isLoading = false, user = resource.data)
                        val user = _uiState.value.user
                        user?.let {
                            _uiState.value = _uiState.value.copy(name = user.firstName, surname = user.lastName, email = user.email )
                        }
                    }
                    is Resource.Error->{
                        _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)

                    }
                }
            }
        }
    }

    fun onClickSave(){

        if (
            _uiState.value.name == _uiState.value.user?.firstName
            && _uiState.value.surname == _uiState.value.user?.lastName
            && _uiState.value.email == _uiState.value.user?.email ){

        }
        else{

            viewModelScope.launch {
                _uiState.value = _uiState.value.copy(isLoading = true)
                updateUserByIdUseCase.invoke(application.getUserId(), UpdateUserRequest(_uiState.value.name,_uiState.value.surname,_uiState.value.email)).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            _uiState.value = _uiState.value.copy(isLoading = false, user = resource.data)
                            val user = _uiState.value.user
                            user?.let {
                                _uiState.value = _uiState.value.copy(name = user.firstName, surname = user.lastName, email = user.email )
                            }
                        }
                        is Resource.Error->{
                            _uiState.value = _uiState.value.copy(isLoading = false, error = resource.message)

                        }
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

data class EditProfileUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: User? =  null,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = ""
)