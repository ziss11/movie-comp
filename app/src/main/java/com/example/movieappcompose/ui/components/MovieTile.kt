package com.example.movieappcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.ui.theme.Grey
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.theme.Yellow

@Composable
fun MovieTile(
    trailing: @Composable () -> Unit,
    title: String,
    subtitle: String,
    threeLine: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        trailing()
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = Grey
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.movie_rating),
                    tint = Yellow,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = threeLine,
                    style = MaterialTheme.typography.body2.copy(
                        color = Grey
                    )
                )
            }
        }
    }
}
