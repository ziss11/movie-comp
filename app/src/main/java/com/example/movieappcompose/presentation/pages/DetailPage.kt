package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.presentation.components.ContentSection
import com.example.movieappcompose.presentation.theme.Grey
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme

@Composable
fun DetailPage(
    movieId: Int,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { DetailTopBar() },
        modifier = modifier,
    ) {
        DetailContent(
            image = R.drawable.example_movie1,
            title = "Avatar",
            tagline = "SuperHero",
            overview = "akdngklahga ajkbhjakbajfbhakfbhdfjkbhfdkbkdfjbhakfdj njhbjkfb"
        )
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    tagline: String,
    overview: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .height(370.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = tagline,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        GenreItem(
            name = "Action",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContentSection(title = stringResource(id = R.string.overview)) {
            Text(
                text = overview,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.body1.copy(
                    color = Grey
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun GenreItem(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body2.copy(
                color = Grey
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun DetailTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(id = R.string.go_to_previous_page)
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.detail_top_bar_title),
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
                    imageVector = Icons.Default.BookmarkBorder,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        },
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun DetailPagePreview() {
    MovieAppComposeTheme {
        DetailPage(movieId = 851644)
    }
}

