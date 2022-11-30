package com.example.movieappcompose.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun MovieCard(
    @DrawableRes image: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 12.dp,
                            topStart = 12.dp,
                        )
                    ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
            RatingItem(
                rating = subtitle.toDoubleOrNull() ?: 0.0,
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 4.dp,
                    bottom = 12.dp
                ),
            )
        }
    }
}
