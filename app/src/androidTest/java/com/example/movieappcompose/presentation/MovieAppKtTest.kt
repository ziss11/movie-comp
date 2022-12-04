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
import com.example.movieappcompose.onNodeWithStringId
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
        overview = """Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian 
        gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to 
        unleash his unique form of justice on the modern world.
        """
    )
    val fakeTopRatedMovieData = Movie(
        id = 238,
        title = "The Godfather",
        posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        overview = """Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American 
        Corleone crime family. When organized crime family patriarch, Vito Corleone barely 
        survives an attempt on his life, his youngest son, Michael steps in to take care of the 
        would-be killers, launching a campaign of bloody revenge."""
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
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.movie).performClick()
        navController.assertCurrentRouteName(Screen.Movie.route)
        composeTestRule.onNodeWithStringId(R.string.watchlist).performClick()
        navController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithStringId(R.string.about).performClick()
        navController.assertCurrentRouteName(Screen..route)
    }

    @Test
    fun navHost_clickItemNowPlayingMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(R.string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertIsDisplayed()
    }

    @Test
    fun navHost_clickItemTopRatedMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(R.string.main_movie_tag_test)
            .performScrollToKey(fakeTopRatedMovieData.id)
        composeTestRule.onNodeWithText(fakeTopRatedMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeTopRatedMovieData.title).assertIsDisplayed()
    }

    @Test
    fun navHost_clickItemSearchMovie_navigatesToDetailWithData(){
        composeTestRule.onNodeWithTagStringId(R.string.search_bar_tag_test).performTextInput("Adam")
        composeTestRule.onNodeWithTagStringId(R.string.main_movie_tag_test)
            .performScrollToKey(fakeNowPlayingMovieData.id)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertIsDisplayed()
    }
}