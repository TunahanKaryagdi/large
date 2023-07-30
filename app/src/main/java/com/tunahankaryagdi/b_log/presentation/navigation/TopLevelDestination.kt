package com.tunahankaryagdi.b_log.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.tunahankaryagdi.b_log.R
import com.tunahankaryagdi.b_log.presentation.add.addRoute
import com.tunahankaryagdi.b_log.presentation.home.homeRoute


enum class TopLevelDestination(

    val route: String,
    val icon: ImageVector,
    val textId: Int
){

    HOME(
        route = homeRoute,
        icon = Icons.Default.Home,
        textId = R.string.home
    ),
    ADD(
        route = addRoute,
        icon = Icons.Default.Add,
        textId = R.string.add
    )
}