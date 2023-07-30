package com.tunahankaryagdi.b_log.presentation.add

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val addRoute = "add"

fun NavController.navigateToAdd(
    navOptions: NavOptions? = null
){
    this.navigate(addRoute,navOptions)
}

fun NavGraphBuilder.addScreen(){

    composable(addRoute){
        AddScreenRoute()
    }
}