package com.example.movieappcompose.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
                navigateBack = navigateBack
            )
        },
        modifier = modifier,
    ) {
        when (searchMoviesResult) {
            is ResultState.Initial -> {}
            is ResultState.Loading -> LoadingScreen()
            is ResultState.Success -> {
                SearchedMoviesContent(
                    searchedMovies = searchMoviesResult.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is ResultState.Error -> ErrorScreen(
                text = stringResource(id = R.string.search_empty)
            )
        }
    }
}

@Composable
fun SearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
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
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier.padding(
                    end = 16.dp,
                )
            )
        },
        modifier = modifier
    )
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        textStyle = MaterialTheme.typography.body2,
        singleLine = true,
        value = query,
        onValueChange = onQueryChange,
        trailingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = stringResource(id = R.string.search_desc)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.query_search_placeholder),
                style = MaterialTheme.typography.body2
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun SearchedMoviesContent(
    searchedMovies: List<MovieModel>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
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