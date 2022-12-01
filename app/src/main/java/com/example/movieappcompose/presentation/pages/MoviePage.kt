package com.example.movieappcompose.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
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
import com.example.movieappcompose.presentation.components.ContentSection
import com.example.movieappcompose.presentation.components.MovieCard
import com.example.movieappcompose.presentation.components.MovieTile
import com.example.movieappcompose.presentation.components.SectionText
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme

@Composable
fun MoviePage(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        item {
            ContentSection(
                title = "Popular Movie",
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(count = 5) {
                        MovieCard(
                            image = R.drawable.example_movie1,
                            contentDescription = "Avatar",
                        )
                    }
                }
            }
        }
        item {
            SectionText(text = "Recommended Movie")
        }
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
fun MoviePageTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
        title = {
            Text(
                text = stringResource(id = R.string.movie_top_bar_title),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
        actions = {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        },
        modifier = modifier
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoviePagePreview() {
    MovieAppComposeTheme {
        MoviePage()
    }
}