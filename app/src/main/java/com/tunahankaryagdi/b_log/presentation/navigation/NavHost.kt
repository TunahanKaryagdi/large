package com.tunahankaryagdi.b_log.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tunahankaryagdi.b_log.presentation.add.addScreen
import com.tunahankaryagdi.b_log.presentation.home.homeRoute
import com.tunahankaryagdi.b_log.presentation.home.homeScreen
import com.tunahankaryagdi.b_log.presentation.login.loginScreen
import com.tunahankaryagdi.b_log.presentation.signup.signupRoute
import com.tunahankaryagdi.b_log.presentation.signup.signupScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        loginScreen(
            navigateToSignup = { navController.navigate(signupRoute) },
            navigateToHome = {navController.navigate(homeRoute)}
        )

        signupScreen()

        homeScreen()

        addScreen()
    }
}