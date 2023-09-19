package com.tunahankaryagdi.b_log.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tunahankaryagdi.b_log.presentation.appstate.rememberMainAppState


const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
) {
    this.navigate(homeRoute, navOptions)
}


fun NavGraphBuilder.homeScreen(
    navigateToAddScreen: () ->Unit,
    navigateToDetailScreen: (String) ->Unit
){

    composable(homeRoute){
        HomeScreenRoute(
            navigateToAddScreen = navigateToAddScreen,
            navigateToDetailScreen = navigateToDetailScreen
        )
    }
}