package com.tunahankaryagdi.b_log.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = mainDarkColorLight,
    secondary = secondaryBlueLight,
    background = backgroundColorLight,
    onPrimary = lightTextColorLight,
    onSecondary = grayTextColorLight,
    onBackground = mainDarkColorLight,
)

private val LightColorScheme = lightColorScheme(
    primary = mainDarkColorLight,
    secondary = secondaryBlueLight,
    background = backgroundColorLight,
    onPrimary = lightTextColorLight,
    onSecondary = grayTextColorLight,
    onBackground = mainDarkColorLight,



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
@Composable
fun BlogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}


