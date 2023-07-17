package com.tunahankaryagdi.b_log.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.b_log.data.model.LoginRequest
import com.tunahankaryagdi.b_log.data.repository.AuthRepository
import com.tunahankaryagdi.b_log.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {


    fun login(){

        viewModelScope.launch {

            val result = authRepository.login(LoginRequest("",""))

            when(result){
                is Resource.Success->{
                    println(result.data.message)
                }
                is Resource.Error->{
                    println(result.message)
                }
            }


        }


    }



}