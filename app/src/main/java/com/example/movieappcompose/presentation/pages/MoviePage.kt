package com.example.movieappcompose.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.R
import com.example.movieappcompose.ViewModelFactory
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.presentation.components.ContentSection
import com.example.movieappcompose.presentation.components.MovieCard
import com.example.movieappcompose.presentation.components.MovieTile
import com.example.movieappcompose.presentation.components.SectionText
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.presentation.viewmodels.MovieViewModel
import com.example.movieappcompose.utilities.ResultState

@Composable
fun MoviePage(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel(factory = ViewModelFactory.getInstance())
) {
    val popularMoviesResult = viewModel.fetchPopularMovies(BuildConfig.API_KEY)

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        item {
            ContentSection(
                title = "Popular Movie",
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                when (popularMoviesResult) {
                    is ResultState.Loading -> LoadingScreen()
                    is ResultState.Success -> PopularMovieResultScreen(popularMoviesResult.data)
                    is ResultState.Error -> ErrorScreen(retryAction = {})
                }
            }
        }
        item {
            SectionText(text = "Recommended Movie")
        }
        items(count = 5) {
            MovieTile(
                leading = {
                    Image(
                        painter = painterResource(id = R.drawable.example_movie1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(76.dp)
                            .width(76.dp)
                    )
                },
                title = "Avatar",
                subtitle = "Afgkg gjggjkgkgglggblgbljv b bjbfbngb jggb gbnjgdnvdbgjgbj",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

}

@Composable
fun MoviePageTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
        title = {
            Text(
                text = stringResource(id = R.string.movie_top_bar_title),
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
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}

@Composable
fun PopularMovieResultScreen(
    popularMovies: List<MovieModel>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier,
    ) {
        items(popularMovies) { item ->
            MovieCard(
                imageUrl = item.posterPath.toString(),
                contentDescription = item.title.toString(),
            )
        }
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.load_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.reload))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoviePagePreview() {
    MovieAppComposeTheme {
        MoviePage()
    }
}