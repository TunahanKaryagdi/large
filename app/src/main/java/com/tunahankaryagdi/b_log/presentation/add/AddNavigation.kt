package com.tunahankaryagdi.b_log.presentation.add

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val addRoute = "add"

fun NavGraphBuilder.addScreen(){

    composable(addRoute){
        AddScreenRoute()
    }
}