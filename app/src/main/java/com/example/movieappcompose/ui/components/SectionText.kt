package com.example.movieappcompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.MovieApp
import com.example.movieappcompose.ui.theme.MovieAppComposeTheme

@Composable
fun SectionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}