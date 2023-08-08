package com.tunahankaryagdi.b_log.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier
) {
    ProfileScreen(
        modifier = modifier
    )
}


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {


    Box(modifier = modifier, contentAlignment = Alignment.Center){
        Text(text = "ProfileScreen")
    }

}