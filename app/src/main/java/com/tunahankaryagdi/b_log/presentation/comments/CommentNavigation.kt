package com.tunahankaryagdi.b_log.presentation.comments

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val commentsRoute = "comments"

fun NavController.navigateToComments(
    id: String,
    navOptions: NavOptions? = null
){
    this.navigate("$commentsRoute/$id",navOptions)
}

fun NavGraphBuilder.commentsScreen(){
    composable("$commentsRoute/{blogId}"){
        CommentsScreenRoute()
    }
}