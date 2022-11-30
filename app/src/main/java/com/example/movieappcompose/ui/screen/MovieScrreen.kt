package com.example.movieappcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.components.ContentSection
import com.example.movieappcompose.ui.components.MovieCard

@Composable
fun MovieScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.scrollable(
            rememberScrollState(),
            orientation = Orientation.Vertical
        )
    ) {
        for (i in 1..3) {
            ContentSection(
                title = "Popular Movie",
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(count = 5) {
                        MovieCard(modifier = Modifier.height(150.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.example_movie1),
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}