package com.example.movieappcompose.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.movieappcompose.onNodeWithTagStringId
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.utilities.Screen
import com.example.movieappcompose.R
import com.example.movieappcompose.assertCurrentRouteName
import com.example.movieappcompose.domain.entities.Movie
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    val fakeNowPlayingMovieData = Movie(
        id = 436270,
        title = "Black Adam",
        posterPath = "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world."
    )

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
        navController.assertCurrentRouteName(Screen.Movie.route)
    }

    @Test
    fun navHost_clickItemNowPlayingMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(R.string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertIsDisplayed()
    }
}