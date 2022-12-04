package com.example.movieappcompose.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.utilities.Screen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieAppComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MovieApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        val expectedRoute = Screen.Movie.route
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(expectedRoute, currentRoute)
    }
}