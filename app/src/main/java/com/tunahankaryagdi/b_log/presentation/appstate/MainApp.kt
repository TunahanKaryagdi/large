package com.tunahankaryagdi.b_log.presentation.appstate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tunahankaryagdi.b_log.presentation.home.homeRoute
import com.tunahankaryagdi.b_log.presentation.login.loginRoute
import com.tunahankaryagdi.b_log.presentation.navigation.NavigationHost
import com.tunahankaryagdi.b_log.presentation.navigation.TopLevelDestination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    appState: MainAppState = rememberMainAppState()
) {

    Scaffold(
        modifier = modifier,
        bottomBar = {

            AnimatedVisibility(visible = appState.shouldShowBottomBar) {
                AppNavBar(
                    destinations = AppDestinations(appState.topLevelDestinations),
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }


            
        }
    )
    {
        NavigationHost(
            navController = appState.navController,
            startDestination = homeRoute,
            modifier = modifier.padding(it)
        )
    }


}

data class AppDestinations(
    val destinations: List<TopLevelDestination>
) : List<TopLevelDestination> by destinations


@Composable
internal fun AppNavBar(
    destinations: AppDestinations,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 15.dp,
        modifier = Modifier
    ){

        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                label = { Text(text = stringResource(id = destination.textId)) },
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(imageVector = destination.icon, contentDescription = stringResource(id = destination.textId))
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    }
}