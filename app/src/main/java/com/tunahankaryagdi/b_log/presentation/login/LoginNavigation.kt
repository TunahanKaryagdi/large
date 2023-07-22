package com.tunahankaryagdi.b_log.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val loginRoute = "login"
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