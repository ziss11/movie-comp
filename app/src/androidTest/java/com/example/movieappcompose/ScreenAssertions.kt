package com.example.movieappcompose

import androidx.navigation.NavController
import org.junit.Assert

fun NavController.assertCurrentRouteName(expectedRoute: String) {
    Assert.assertEquals(expectedRoute, currentBackStackEntry?.destination?.route)
}