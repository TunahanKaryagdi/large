package com.tunahankaryagdi.b_log.presentation.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val splashRoute = "splash"



fun NavGraphBuilder.splashScreen(
    navigateToHome: ()->Unit,
    navigateToLogin: ()->Unit,
){
    composable(splashRoute){
        SplashScreenRoute(
            navigateToLogin = navigateToLogin,
            navigateToHome = navigateToHome
        )
    }
}