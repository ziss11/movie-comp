package com.example.movieappcompose.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movieappcompose.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.load_failed),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = text, style = MaterialTheme.typography.body2)
    }
}