package com.example.movieappcompose.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.components.ContentSection
import com.example.movieappcompose.ui.components.MovieCard
import com.example.movieappcompose.ui.components.MovieTile
import com.example.movieappcompose.ui.components.SectionText
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme

@Composable
fun MoviePage(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                            title = "Avatar",
                            subtitle = 4.8.toString(),
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
                trailing = {
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
                subtitle = "Action",
                threeLine = 4.8.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoviePagePreview() {
    MovieAppComposeTheme {
        MoviePage()
    }
}