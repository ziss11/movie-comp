package com.example.movieappcompose.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieappcompose.utilities.Screen

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
)