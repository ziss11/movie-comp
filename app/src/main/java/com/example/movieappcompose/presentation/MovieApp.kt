package com.example.movieappcompose.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.navigation.NavigationItem
import com.example.movieappcompose.utilities.Screen
import com.example.movieappcompose.presentation.pages.*
import com.example.movieappcompose.presentation.theme.Grey
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Movie.route -> MoviePageTopBar(
                    navigateToSearch = {
                        navController.navigate(Screen.SearchMovies.route)
                    }
                )
                Screen.Watchlist.route -> WatchlistPageTopBar()
                Screen.About.route -> AboutPageTopBar()
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Detail.route && currentRoute != Screen.SearchMovies.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movie.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Movie.route) {
                MoviePage(
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    }
                )
            }
            composable(Screen.Watchlist.route) {
                WatchlistPage()
            }
            composable(Screen.About.route) {
                AboutPage()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") {
                    type = NavType.IntType
                })
            ) {
                val movieId = it.arguments?.getInt("movieId") ?: 0

                DetailPage(
                    movieId = movieId,
                    navigateBack = { navController.navigateUp() },
                    navigateAnotherDetail = { anotherMovieId ->
                        navController.navigate(Screen.Detail.createRoute(anotherMovieId))
                    }
                )
            }
            composable(Screen.SearchMovies.route) {
                SearchPage(
                    navigateBack = { navController.navigateUp() },
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.movie),
            icon = Icons.Outlined.Movie,
            screen = Screen.Movie,
        ),
        NavigationItem(
            title = stringResource(id = R.string.watchlist),
            icon = Icons.Outlined.Bookmarks,
            screen = Screen.Watchlist,
        ),
        NavigationItem(
            title = stringResource(id = R.string.about),
            icon = Icons.Outlined.Person,
            screen = Screen.About,
        )
    )

    BottomNavigation(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        navigationItems.map { item ->
            BottomBarItem(
                label = item.title,
                icon = item.icon,
                route = item.screen.route,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.BottomBarItem(
    label: String, icon: ImageVector, route: String, navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
            )
        },
        label = { Text(text = label) },
        unselectedContentColor = Grey,
        selectedContentColor = MaterialTheme.colors.primary,
        selected = currentRoute == route,
        onClick = {
            navController.popBackStack()
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        },
    )
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun HomePagePreview() {
    MovieAppComposeTheme {
        MovieApp()
    }
}