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
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.example.movieappcompose.data.models.GenreModel
import com.example.movieappcompose.data.models.MovieModel
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
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory.getInstance())
) {
    val movieDetailResult: ResultState<MovieModel> = viewModel.movieDetailResult


    Scaffold(
        topBar = { DetailTopBar(navigateBack = navigateBack) },
        modifier = modifier,
    ) { innerPadding ->
        when (movieDetailResult) {
            is ResultState.Loading -> {
                LoadingScreen()
                viewModel.fetchMovieDetail(movieId)
            }
            is ResultState.Success -> {
                val data = movieDetailResult.data
                DetailContent(
                    id = data.id,
                    imageUrl = data.backdropPath.toString(),
                    title = data.title.toString(),
                    tagline = data.tagline,
                    genres = data.genres ?: listOf(),
                    overview = data.overview.toString(),
                    viewModel = viewModel,
                    navigateToDetail = navigateAnotherDetail,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is ResultState.Error -> ErrorScreen()
        }
    }
}

@Composable
fun DetailTopBar(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
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
                    imageVector = Icons.Default.BookmarkBorder,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(id = R.string.search_desc)
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun DetailContent(
    id: Int,
    imageUrl: String,
    title: String,
    tagline: String?,
    genres: List<GenreModel>?,
    overview: String,
    viewModel: DetailViewModel,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendationMoviesResult: ResultState<List<MovieModel>> =
        viewModel.recommendationMoviesResult

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = BuildConfig.IMAGE_BASE_URL + imageUrl,
            contentDescription = title,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_broken_image_white),
            placeholder = painterResource(id = R.drawable.ic_image_white),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (!tagline.isNullOrBlank()) {
            Text(
                text = tagline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.body1.copy(
                    color = Grey
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        if (genres != null) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                genres.map { item ->
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
                text = overview,
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
                    viewModel.fetchRecommendationMovies(id)
                }
                is ResultState.Success -> {
                    RecommendationMovieContent(
                        recommendationMovies = recommendationMoviesResult.data,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is ResultState.Error -> ErrorScreen()
            }
        }
    }
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
    recommendationMovies: List<MovieModel>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier,
    ) {
        items(recommendationMovies) { item ->
            MovieCard(
                imageUrl = item.posterPath.toString(),
                contentDescription = item.title.toString(),
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

