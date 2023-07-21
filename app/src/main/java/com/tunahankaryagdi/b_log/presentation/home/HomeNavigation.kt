package com.tunahankaryagdi.b_log.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tunahankaryagdi.b_log.presentation.login.LoginScreenRoute


const val homeRoute = "home"
fun NavGraphBuilder.homeScreen(
){

    composable(homeRoute){
        HomeScreenRoute()
    }
}