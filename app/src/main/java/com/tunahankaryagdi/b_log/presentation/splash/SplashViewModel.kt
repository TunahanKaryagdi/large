package com.tunahankaryagdi.b_log.presentation.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.di.MyApplication
import com.tunahankaryagdi.b_log.utils.JwtHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val application: MyApplication
) : ViewModel() {

    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState())
    val uiState = _uiState

    init {
        isLogged()
    }

    private fun isLogged(){
        viewModelScope.launch {
            delay(2000)
            authDataStore.getAccessToken.collect{token->

                if (token.isNotBlank()){

                    if (JwtHelper.isTokenValid(token)){
                        application.setUserId(JwtHelper.decodeAndGetId(token) ?: "")
                        _uiState.value = _uiState.value.copy(token =token, navigateToHome = true)
                    }
                    else{
                        //Session expired
                        println("session expired")
                        authDataStore.saveTokens("","")
                        _uiState.value = _uiState.value.copy( navigateToLogin = true)
                    }
                }
                else{
                    _uiState.value = _uiState.value.copy( navigateToLogin = true)
                }

            }
        }

    }
}


data class SplashUiState(
    val navigateToHome :Boolean = false,
    val navigateToLogin: Boolean = false,
    val token :String = ""
)