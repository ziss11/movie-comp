package com.example.movieappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    secondary = Purple,
    background = Black,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
)

private val LightColorPalette = lightColors(
    primary = White,
    secondary = Purple,
    background = White,
    onPrimary = Black,
    onSecondary = White,
    onBackground = Black,
)

@Composable
fun MovieAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}