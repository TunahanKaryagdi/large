package com.tunahankaryagdi.b_log.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun SplashScreenRoute(
    modifier: Modifier =Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: ()->Unit,
    navigateToLogin: ()->Unit
) {

    LaunchedEffect(key1 = viewModel){
        viewModel.uiState.collect{
            if (it.navigateToHome){
                navigateToHome()
            }
            if (it.navigateToLogin){
                navigateToLogin()
            }
        }

    }

    SplashScreen(modifier = modifier)
}


@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Image(imageVector = Icons.Default.Person, contentDescription = "Logo")
    }
}