package com.example.movieappcompose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.R

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
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.ic_image),
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp)),
    )
}
