package com.tunahankaryagdi.b_log.presentation.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val detailRoute = "detail"


fun NavController.navigateToDetail(
    id: String,
    navOptions: NavOptions? = null
){
    this.navigate("$detailRoute/$id",navOptions)
}


fun NavGraphBuilder.detailScreen(){
    composable("$detailRoute/{blogId}"){
        DetailScreenRoute()
    }
}