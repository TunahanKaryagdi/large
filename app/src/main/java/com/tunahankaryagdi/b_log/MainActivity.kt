package com.tunahankaryagdi.b_log

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tunahankaryagdi.b_log.presentation.add.addRoute
import com.tunahankaryagdi.b_log.presentation.home.homeRoute
import com.tunahankaryagdi.b_log.presentation.login.LoginScreen
import com.tunahankaryagdi.b_log.presentation.login.loginRoute
import com.tunahankaryagdi.b_log.presentation.navigation.NavigationHost
import com.tunahankaryagdi.b_log.ui.theme.BlogTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(navController = navController, startDestination = addRoute)
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BlogTheme {
        Greeting("Android")
    }
}