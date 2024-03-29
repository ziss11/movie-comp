package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
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
    val query by viewModel.query

    val topRatedMoviesResult by viewModel.fetchTopRatedMovies()
        .observeAsState(initial = ResultState.Loading)
    val nowPlayingMoviesResult by viewModel.fetchNowPlayingMovies()
        .observeAsState(initial = ResultState.Loading)
    val searchMoviesResult by viewModel.searchMovies(query)
        .observeAsState(initial = ResultState.Loading)

    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag(stringResource(id = R.string.main_movie_tag_test)),
    ) {
        item {
            SearchBar(
                query = query,
                onQueryChange = { newQuery ->
                    viewModel.searchMovies(newQuery = newQuery)
                },
                onClearQuery = {
                    viewModel.searchMovies(newQuery = "")
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .testTag(stringResource(id = R.string.search_bar_tag_test))
            )
        }

        if (query.isEmpty()) {
            initialMoviesScreen(
                nowPlayingMoviesResult = nowPlayingMoviesResult,
                topRatedMoviesResult = topRatedMoviesResult,
                navigateToDetail = navigateToDetail,
            )
        } else {
            when (searchMoviesResult) {
                is ResultState.Loading -> item { LoadingScreen() }
                is ResultState.Success -> {
                    val data = (searchMoviesResult as ResultState.Success<List<Movie>>).data

                    if (data.isNotEmpty()) {
                        searchedMoviesScreen(
                            searchedMovies = data,
                            navigateToDetail = navigateToDetail,
                        )
                    } else {
                        item {
                            ErrorScreen(
                                text = stringResource(id = R.string.search_empty),
                                modifier = Modifier.height(500.dp)
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

fun LazyListScope.searchedMoviesScreen(
    searchedMovies: List<Movie>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    items(searchedMovies, key = { it.id }) { item ->
        MovieTile(
            imageUrl = item.posterPath ?: "",
            title = item.title,
            subtitle = item.overview ?: "",
            onClick = { navigateToDetail(item.id) },
            modifier = modifier
                .padding(horizontal = 16.dp)
                .testTag(item.title),
        )
    }
}

fun LazyListScope.initialMoviesScreen(
    nowPlayingMoviesResult: ResultState<List<Movie>>,
    topRatedMoviesResult: ResultState<List<Movie>>,
    navigateToDetail: (Int) -> Unit,
) {
    item {
        ContentSection(
            title = "Now Playing", modifier = Modifier.padding(bottom = 16.dp)
        ) {
            when (nowPlayingMoviesResult) {
                is ResultState.Loading -> LoadingScreen()
                is ResultState.Success -> NowPlayingMovieResultScreen(
                    nowPlayingMovies = nowPlayingMoviesResult.data,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier.testTag(stringResource(id = R.string.now_playing_tag_test))
                )

                is ResultState.Error -> ErrorScreen(
                    text = stringResource(R.string.movie_empty), modifier = Modifier.height(200.dp)
                )
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
            items(topRatedMoviesResult.data, key = { it.id }) { item ->
                MovieTile(
                    imageUrl = item.posterPath ?: "",
                    title = item.title,
                    subtitle = item.overview ?: "",
                    onClick = { navigateToDetail(item.id) },
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }

        is ResultState.Error -> {
            item {
                ErrorScreen(
                    text = stringResource(R.string.movie_empty), modifier = Modifier.height(200.dp)
                )
            }
        }
    }
}

@Composable
fun NowPlayingMovieResultScreen(
    navigateToDetail: (Int) -> Unit,
    nowPlayingMovies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier,
    ) {
        items(nowPlayingMovies, key = { it.id }) { item ->
            MovieCard(
                imageUrl = item.posterPath ?: "",
                contentDescription = item.title,
                onClick = { navigateToDetail(item.id) },
            )
        }
    }
}

@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true
)
@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true
)
@Composable
fun MoviePagePreview() {
    MovieAppComposeTheme {
        MoviePage(navigateToDetail = {})
    }
}