package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieappcompose.R
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.presentation.ViewModelFactory
import com.example.movieappcompose.presentation.components.*
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.presentation.viewmodels.MovieViewModel
import com.example.movieappcompose.utilities.ResultState

@Composable
fun MoviePage(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val nowPlayingMoviesResult = viewModel.nowPlayingMoviesResult
    val topRatedMoviesResult = viewModel.topRatedMoviesResult

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        item {
            ContentSection(
                title = "Now Playing",
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                when (nowPlayingMoviesResult) {
                    is ResultState.Loading -> LoadingScreen()
                    is ResultState.Success -> PopularMovieResultScreen(
                        popularMovies = nowPlayingMoviesResult.data,
                        navigateToDetail = navigateToDetail,
                    )
                    is ResultState.Error -> ErrorScreen(
                        text = stringResource(R.string.movie_empty)
                    )
                    else -> {}
                }
            }
        }
        item {
            SectionText(text = "Top Rated Movie")
        }
        when (topRatedMoviesResult) {
            is ResultState.Loading -> {
                item { LoadingScreen() }
            }
            is ResultState.Success -> {
                items(topRatedMoviesResult.data) { item ->
                    NowPlayingMovieResultScreen(
                        id = item.id,
                        imageUrl = item.posterPath ?: "",
                        title = item.title,
                        overview = item.overview ?: "",
                        navigateToDetail = navigateToDetail,
                    )
                }
            }
            is ResultState.Error -> {
                item {
                    ErrorScreen(
                        text = stringResource(R.string.movie_empty)
                    )
                }
            }
            else -> {}
        }
    }
}

@Composable
fun MoviePageTopBar(
    navigateToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        title = {
            Text(
                text = stringResource(id = R.string.movie_top_bar_title),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
        actions = {
            IconButton(
                onClick = navigateToSearch,
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
fun PopularMovieResultScreen(
    navigateToDetail: (Int) -> Unit,
    popularMovies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier,
    ) {
        items(popularMovies) { item ->
            MovieCard(
                imageUrl = item.posterPath ?: "",
                contentDescription = item.title,
                onClick = { navigateToDetail(item.id) },
            )
        }
    }
}

@Composable
fun NowPlayingMovieResultScreen(
    id: Int,
    imageUrl: String,
    title: String,
    overview: String,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    MovieTile(
        imageUrl = imageUrl,
        title = title,
        subtitle = overview,
        onClick = { navigateToDetail(id) },
        modifier = modifier.padding(horizontal = 16.dp),
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
fun MoviePagePreview() {
    MovieAppComposeTheme {
        MoviePage(navigateToDetail = {})
    }
}