package com.example.movieappcompose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieappcompose.ui.navigation.NavigationItem
import com.example.movieappcompose.ui.navigation.Screen
import com.example.movieappcompose.ui.screen.*
import com.example.movieappcompose.ui.theme.Grey
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier.background(MaterialTheme.colors.background)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movie.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Movie.route) {
                MovieScreen()
            }
            composable(Screen.Series.route) {
                SeriesScreen()
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(60.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.top_bar_title),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(id = R.string.movie),
            icon = Icons.Outlined.Movie,
            screen = Screen.Movie,
        ),
        NavigationItem(
            title = stringResource(id = R.string.series),
            icon = Icons.Outlined.Tv,
            screen = Screen.Series,
        ),
        NavigationItem(
            title = stringResource(id = R.string.watchlist),
            icon = Icons.Outlined.Bookmark,
            screen = Screen.Watchlist,
        ),
        NavigationItem(
            title = stringResource(id = R.string.profile),
            icon = Icons.Outlined.Person,
            screen = Screen.Profile,
        )
    )

    BottomNavigation(modifier = modifier.background(MaterialTheme.colors.background)) {
        navigationItems.map { item ->
            BottomBarItem(
                label = item.title, icon = item.icon,
                route = item.screen.route,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.BottomBarItem(
    label: String,
    icon: ImageVector,
    route: String,
    navController: NavHostController
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
        selectedContentColor = MaterialTheme.colors.secondary,
        selected = currentRoute == route,
        onClick = {
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

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomePagePreview() {
    MovieAppComposeTheme {
        MovieApp()
    }
}