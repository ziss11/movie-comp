package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieappcompose.R
import com.example.movieappcompose.data.models.MovieModel
import com.example.movieappcompose.presentation.ViewModelFactory
import com.example.movieappcompose.presentation.components.ErrorScreen
import com.example.movieappcompose.presentation.components.LoadingScreen
import com.example.movieappcompose.presentation.components.MovieTile
import com.example.movieappcompose.presentation.theme.MovieAppComposeTheme
import com.example.movieappcompose.presentation.viewmodels.SearchViewModel
import com.example.movieappcompose.utilities.ResultState

@Composable
fun SearchPage(
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.getInstance())
) {
    val query by viewModel.query
    val searchMoviesResult = viewModel.searchMovieResult

    Scaffold(
        topBar = {
            SearchTopBar(
                query = query,
                onQueryChange = { newQuery ->
                    viewModel.searchMovies(newQuery = newQuery)
                },
                onClearQuery = {
                    viewModel.searchMovies(newQuery = "")
                },
                navigateBack = navigateBack,
            )
        },
        modifier = modifier,
    ) {
        when (searchMoviesResult) {
            is ResultState.Initial -> {}
            is ResultState.Loading -> LoadingScreen()
            is ResultState.Success -> {
                val data = searchMoviesResult.data

                if (data.isNotEmpty()) {
                    SearchedMoviesContent(
                        searchedMovies = searchMoviesResult.data,
                        navigateToDetail = navigateToDetail
                    )
                } else {
                    ErrorScreen(text = stringResource(R.string.search_empty))
                }
            }
            is ResultState.Error -> {}
        }
    }
}

@Composable
fun SearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onClearQuery = onClearQuery,
                modifier = Modifier.padding(end = 6.dp)
            )
        },
        actions = {
            TextButton(
                onClick = navigateBack,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                )
            }
        },
        modifier = modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        value = query,
        onValueChange = onQueryChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = stringResource(id = R.string.search_desc)
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = onClearQuery,
                ) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = stringResource(id = R.string.clear_query)
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.query_search_placeholder),
                style = MaterialTheme.typography.body1
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun SearchedMoviesContent(
    searchedMovies: List<MovieModel>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        items(searchedMovies) { item ->
            MovieTile(
                imageUrl = item.posterPath.toString(),
                title = item.title.toString(),
                subtitle = item.overview.toString(),
                onClick = { navigateToDetail(item.id) },
                modifier = modifier.padding(horizontal = 16.dp),
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
fun SearchPagePreview() {
    MovieAppComposeTheme {
        SearchPage(
            navigateBack = {},
            navigateToDetail = {},
        )
    }
}