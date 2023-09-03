package com.tunahankaryagdi.b_log.presentation.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tunahankaryagdi.b_log.presentation.login.LoginScreenRoute

const val signupRoute = "signup"

fun NavController.navigateToSignup(
    navOptions: NavOptions? = null
){
    this.navigate(signupRoute,navOptions)
}

fun NavGraphBuilder.signupScreen(
    navigateToLogin: ()->Unit
){

    composable(signupRoute){
        SignupScreenRoute(
            navigateToLogin = navigateToLogin
        )
    }
}