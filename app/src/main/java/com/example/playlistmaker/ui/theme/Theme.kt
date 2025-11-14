package com.example.playlistmaker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

private val DarkColorScheme = darkColorScheme(
    primary = AlmostBlack,
    onPrimary = White,
    secondary = White,
    onSecondary = AlmostBlack,
    tertiary = White,
    primaryContainer = White,
    onPrimaryContainer = AlmostBlack
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = AlmostBlack,
    secondary = AlmostBlack,
    onSecondary = White,
    tertiary = Gray,
    primaryContainer = LightGray,
    onPrimaryContainer = Gray
)

@Composable
fun PlaylistMakerTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = remember(isDarkTheme) {
        if (isDarkTheme) DarkColorScheme else LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

fun IsDarkTheme(theme: Theme): Boolean {
    return theme == Theme.DARK
}