package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieappcompose.BuildConfig
import com.example.movieappcompose.R
import com.example.movieappcompose.domain.entities.Movie
import com.example.movieappcompose.presentation.ViewModelFactory
import com.example.movieappcompose.presentation.components.ContentSection
import com.example.movieappcompose.presentation.components.ErrorScreen
import com.example.movieappcompose.presentation.components.LoadingScreen
import com.example.movieappcompose.presentation.components.MovieCard
import com.example.movieappcompose.presentation.theme.Grey
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.presentation.viewmodels.DetailViewModel
import com.example.movieappcompose.utilities.ResultState

@Composable
fun DetailPage(
    movieId: Int,
    navigateBack: () -> Unit,
    navigateAnotherDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val movieDetailResult by viewModel.fetchMovieDetail(movieId)
        .observeAsState(initial = ResultState.Loading)

    when (movieDetailResult) {
        is ResultState.Loading -> {
            LoadingScreen()
            viewModel.fetchMovieDetail(movieId)
        }
        is ResultState.Success -> {
            val data = (movieDetailResult as ResultState.Success<Movie>).data

            DetailContent(
                movie = data,
                viewModel = viewModel,
                navigateBack = navigateBack,
                navigateToDetail = navigateAnotherDetail,
                modifier = modifier
            )
        }
        is ResultState.Error -> ErrorScreen(
            text = stringResource(R.string.detail_empty)
        )
        else -> {}
    }
}

@Composable
fun DetailContent(
    movie: Movie,
    viewModel: DetailViewModel,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    viewModel.getWatchlistStatus(movie.id)
    val isAddedToWatchlist by viewModel.getWatchlistStatus(movie.id).observeAsState(false)
    val recommendationMoviesResult by viewModel.fetchRecommendationMovies(movie.id)
        .observeAsState(initial = ResultState.Loading)

    Scaffold(
        topBar = {
            DetailTopBar(
                navigateBack = navigateBack,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = BuildConfig.IMAGE_BASE_URL + movie.backdropPath,
                contentDescription = movie.title,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.ic_broken_image_white),
                placeholder = painterResource(id = R.drawable.ic_image_white),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                ) {
                    Text(
                        text = movie.title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.h5,

                        )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (!movie.tagline.isNullOrBlank()) {
                        Text(
                            text = movie.tagline,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            style = MaterialTheme.typography.body1.copy(
                                color = Grey
                            ),
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                IconButton(
                    onClick = {
                        if (isAddedToWatchlist) {
                            viewModel.removeMovieFromWatchlist(movie.id)
                        } else {
                            viewModel.addMovieToWatchlist(movie)
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colors.surface)
                ) {
                    Icon(
                        imageVector = if (isAddedToWatchlist) {
                            Icons.Default.Bookmark
                        } else {
                            Icons.Default.BookmarkBorder
                        },
                        tint = MaterialTheme.colors.primary,
                        contentDescription = stringResource(id = R.string.watchlist_action),
                    )
                }
            }
            if (movie.genres != null) {
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    movie.genres.map { item ->
                        GenreItem(
                            name = item.name ?: "",
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ContentSection(title = stringResource(id = R.string.overview)) {
                Text(
                    text = movie.overview ?: "",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.body1.copy(
                        color = Grey
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            ContentSection(
                title = stringResource(id = R.string.recommendation),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                when (recommendationMoviesResult) {
                    is ResultState.Loading -> {
                        LoadingScreen()
                        viewModel.fetchRecommendationMovies(movie.id)
                    }
                    is ResultState.Success -> {
                        val data =
                            (recommendationMoviesResult as ResultState.Success<List<Movie>>).data

                        if (data.isNotEmpty()) {
                            RecommendationMovieContent(
                                recommendationMovies = data,
                                navigateToDetail = navigateToDetail,
                            )
                        } else {
                            ErrorScreen(
                                text = stringResource(id = R.string.no_recommendation),
                                modifier = Modifier.height(200.dp)
                            )
                        }
                    }
                    is ResultState.Error -> ErrorScreen()
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun DetailTopBar(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(id = R.string.go_to_previous_page)
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.detail_top_bar_title),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
        modifier = modifier
    )
}

@Composable
fun GenreItem(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body2.copy(
                color = Grey
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun RecommendationMovieContent(
    recommendationMovies: List<Movie>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier,
    ) {
        items(recommendationMovies) { item ->
            MovieCard(
                imageUrl = item.posterPath ?: "",
                contentDescription = item.title,
                onClick = { navigateToDetail(item.id) },
            )
        }
    }
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
fun DetailPagePreview() {
    MovieAppComposeTheme {
        DetailPage(
            movieId = 851644,
            navigateBack = {},
            navigateAnotherDetail = {}
        )
    }
}

