package com.example.movieappcompose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.theme.Grey

@Composable
fun MovieTile(
    imageUrl: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier,
    ) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(4.dp),
        ) {
            Column(
                modifier.padding(
                    start = 80.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.body2.copy(
                        color = Grey
                    )
                )
            }
        }
        AsyncImage(
            model = BuildConfig.IMAGE_BASE_URL + imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Inside,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.ic_image),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .height(120.dp)
        )
    }
}
