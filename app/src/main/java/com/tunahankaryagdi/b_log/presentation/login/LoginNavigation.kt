package com.tunahankaryagdi.b_log.presentation.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val loginRoute = "login"

fun NavController.navigateToLogin(navOptions: NavOptions? = null){
    this.navigate(loginRoute,navOptions)
}

fun NavGraphBuilder.loginScreen(
    navigateToSignup: ()->Unit,
    navigateToHome: () -> Unit
){

    composable(loginRoute){
        LoginScreenRoute(
            navigateToSignup = navigateToSignup,
            navigateToHome = navigateToHome
        )
    }
}