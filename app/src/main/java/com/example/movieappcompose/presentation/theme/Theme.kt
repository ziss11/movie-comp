package com.example.movieappcompose.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple,
    secondary = Yellow,
    background = Black,
    surface = BlackVariant,
    onPrimary = White,
    onSecondary = Black,
    onBackground = White,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = Purple,
    secondary = Yellow,
    background = White,
    surface = WhiteVariant,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
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