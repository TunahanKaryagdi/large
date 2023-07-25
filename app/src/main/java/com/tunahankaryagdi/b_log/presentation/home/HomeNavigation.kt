package com.tunahankaryagdi.b_log.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val homeRoute = "home"
fun NavGraphBuilder.homeScreen(
){

    composable(homeRoute){
        HomeScreenRoute()
    }
}