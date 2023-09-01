package com.tunahankaryagdi.b_log.presentation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tunahankaryagdi.b_log.presentation.home.homeRoute
import com.tunahankaryagdi.b_log.presentation.home.navigateToHome


const val profileRoute = "profile"


fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
){
    this.navigate(profileRoute,navOptions)
}


fun NavGraphBuilder.profileScreen(
    navigateToLogin:()->Unit
){

    composable(profileRoute){
        ProfileScreenRoute(
            navigateToLogin = navigateToLogin
        )
    }

}