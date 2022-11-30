package com.example.movieappcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme

@Composable
fun ContentSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        SectionText(
            text = title,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}
