package com.example.movieappcompose.ui.pages

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R

@Composable
fun WatchlistPage(modifier: Modifier = Modifier) {
    Text(text = "Watchlist")
}

@Composable
fun WatchlistPageTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
        title = {
            Text(
                text = stringResource(id = R.string.watchlist),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
        modifier = modifier
    )
}