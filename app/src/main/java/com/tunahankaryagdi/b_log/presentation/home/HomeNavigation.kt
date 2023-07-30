package com.tunahankaryagdi.b_log.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val homeRoute = "home"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
) {
    this.navigate(homeRoute, navOptions)
}


fun NavGraphBuilder.homeScreen(
){

    composable(homeRoute){
        HomeScreenRoute()
    }
}