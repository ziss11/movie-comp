package com.example.movieappcompose.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.movieappcompose.*
import com.example.movieappcompose.R.*
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.utilities.Screen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieAppTest {
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

    // navigation
    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Movie.route)
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(string.movie).performClick()
        navController.assertCurrentRouteName(Screen.Movie.route)
        composeTestRule.onNodeWithStringId(string.watchlist).performClick()
        navController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithStringId(string.about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
    }

    // now playing movie list
    @Test
    fun navHost_clickItemNowPlayingMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertIsDisplayed()
    }

    // top rated movie list
    @Test
    fun navHost_clickItemTopRatedMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(string.main_movie_tag_test)
            .performScrollToKey(fakeTopRatedMovieData.id)
        composeTestRule.onNodeWithText(fakeTopRatedMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeTopRatedMovieData.title).assertIsDisplayed()
    }

    // searched movie list
    @Test
    fun navHost_clickItemSearchMovie_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTagStringId(string.search_bar_tag_test)
            .performTextInput("Black Adam")
        composeTestRule.onNodeWithTagStringId(string.main_movie_tag_test)
            .performScrollToKey(fakeNowPlayingMovieData.id)
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertIsDisplayed()
    }

    @Test
    fun navHost_searchedMovieNotFound() {
        composeTestRule.onNodeWithTagStringId(string.search_bar_tag_test)
            .performTextInput("mniangnasdf")
        composeTestRule.onNodeWithStringId(string.search_empty).assertIsDisplayed()
    }

    // detail
    @Test
    fun navHost_cannotLoadDetailMovie() {
        composeTestRule.onNodeWithTagStringId(string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeContentDescriptionStringId(string.go_to_previous_page).performClick()
        navController.assertCurrentRouteName(Screen.Movie.route)
    }

    // watchlist
    @Test
    fun navHost_movieDisplayedAfterAddedToWatchlist() {
        composeTestRule.onNodeWithTagStringId(string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeContentDescriptionStringId(string.watchlist_action).performClick()
        composeTestRule.onNodeContentDescriptionStringId(string.go_to_previous_page).performClick()
        navController.assertCurrentRouteName(Screen.Movie.route)
        composeTestRule.onNodeWithStringId(string.watchlist).performClick()
        navController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertExists()
    }

    @Test
    fun navHost_movieNotDisplayedAfterRemovedToWatchlist() {
        composeTestRule.onNodeWithTagStringId(string.now_playing_tag_test).performScrollTo()
        composeTestRule.onNodeWithTag(fakeNowPlayingMovieData.title).performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.onNodeContentDescriptionStringId(string.watchlist_action).performClick()
        composeTestRule.onNodeContentDescriptionStringId(string.go_to_previous_page).performClick()
        navController.assertCurrentRouteName(Screen.Movie.route)
        composeTestRule.onNodeWithStringId(string.watchlist).performClick()
        navController.assertCurrentRouteName(Screen.Watchlist.route)
        composeTestRule.onNodeWithText(fakeNowPlayingMovieData.title).assertDoesNotExist()
    }

    // about
    @Test
    fun navHost_shouldDisplayProfileContent(){
        composeTestRule.onNodeWithStringId(string.about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeContentDescriptionStringId(string.profile_desc).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(string.my_name).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(string.my_email).assertIsDisplayed()
    }
}