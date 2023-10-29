package com.tunahankaryagdi.b_log.presentation.saved

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val savedRoute = "saved"


fun NavGraphBuilder.savedScreen(
    navigateToDetailScreen : (String)-> Unit
){
    composable(savedRoute){
        SavedScreenRoute(
            navigateToDetailScreen = navigateToDetailScreen
        )
    }
}

fun NavController.navigateToSaved(
    navOptions: NavOptions? = null
){
    this.navigate(savedRoute,navOptions)
}