package com.tunahankaryagdi.b_log.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tunahankaryagdi.b_log.presentation.add.addScreen
import com.tunahankaryagdi.b_log.presentation.add.navigateToAdd
import com.tunahankaryagdi.b_log.presentation.comments.commentsScreen
import com.tunahankaryagdi.b_log.presentation.comments.navigateToComments
import com.tunahankaryagdi.b_log.presentation.detail.detailScreen
import com.tunahankaryagdi.b_log.presentation.detail.navigateToDetail
import com.tunahankaryagdi.b_log.presentation.edit_profile.editProfileScreen
import com.tunahankaryagdi.b_log.presentation.edit_profile.navigateToEditProfile
import com.tunahankaryagdi.b_log.presentation.home.homeScreen
import com.tunahankaryagdi.b_log.presentation.home.navigateToHome
import com.tunahankaryagdi.b_log.presentation.login.loginScreen
import com.tunahankaryagdi.b_log.presentation.login.navigateToLogin
import com.tunahankaryagdi.b_log.presentation.profile.profileScreen
import com.tunahankaryagdi.b_log.presentation.saved.savedScreen
import com.tunahankaryagdi.b_log.presentation.signup.navigateToSignup
import com.tunahankaryagdi.b_log.presentation.signup.signupScreen
import com.tunahankaryagdi.b_log.presentation.splash.splashScreen


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
            navigateToSignup = { navController.navigateToSignup() },
            navigateToHome = {navController.navigateToHome()}
        )

        signupScreen(
            navigateToLogin = {navController.navigateToLogin()}
        )

        homeScreen(
            navigateToAddScreen = {navController.navigateToAdd()},
            navigateToDetailScreen = {navController.navigateToDetail(it)}
        )

        addScreen()

        profileScreen(
            navigateToLogin = {navController.navigateToLogin()},
            navigateToEditProfile = {navController.navigateToEditProfile()}
        )

        splashScreen(
            navigateToHome = {navController.navigateToHome()},
            navigateToLogin = {navController.navigateToLogin()}
        )

        detailScreen(
            navigateToComments = {navController.navigateToComments(it)}
        )

        commentsScreen()

        editProfileScreen()

        savedScreen(
            navigateToDetailScreen = {navController.navigateToDetail(it)}
        )
    }
}