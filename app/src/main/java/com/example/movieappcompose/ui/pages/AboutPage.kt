package com.example.movieappcompose.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme

@Composable
fun AboutPage(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.profile_desc),
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(100))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.my_name),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.my_email),
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun AboutPageTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
        title = {
            Text(
                text = stringResource(id = R.string.about_top_bar_title),
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
fun AboutPagePreview() {
    MovieAppComposeTheme {
        AboutPage()
    }
}