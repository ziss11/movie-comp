package com.example.movieappcompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.theme.Grey

@Composable
fun RatingItem(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(R.string.movie_rating),
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.body2.copy(
                color = Grey
            )
        )
    }
}