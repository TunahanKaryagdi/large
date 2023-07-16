package com.tunahankaryagdi.b_log.presentation.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tunahankaryagdi.b_log.presentation.login.LoginScreenRoute

const val signupRoute = "signup"
fun NavGraphBuilder.signupScreen(
){

    composable(signupRoute){
        SignupScreenRoute()
    }
}