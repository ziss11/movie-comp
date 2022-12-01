package com.example.movieappcompose.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.components.MovieTile
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme

@Composable
fun WatchlistPage(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier,
    ) {
        items(count = 5) {
            MovieTile(
                leading = {
                    Image(
                        painter = painterResource(id = R.drawable.example_movie1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(76.dp)
                            .width(76.dp)
                    )
                },
                title = "Avatar",
                subtitle = "Afgkg gjggjkgkgglggblgbljv b bjbfbngb jggb gbnjgdnvdbgjgbj",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WatchlistPagePreview() {
    MovieAppComposeTheme {
        WatchlistPage()
    }
}