package com.example.movieappcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.ui.theme.Grey

@Composable
fun MovieTile(
    trailing: @Composable () -> Unit,
    title: String,
    subtitle: String,
    threeLine: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 10.dp
            ),
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
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                RatingItem(rating = threeLine.toDoubleOrNull() ?: 0.0)
            }
        }
    }
}
