package com.example.movieappcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieappcompose.BuildConfig

@Composable
fun MovieCard(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = BuildConfig.IMAGE_BASE_URL + imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .height(200.dp)
            .clip(
                RoundedCornerShape(12.dp)
            ),
    )
}
