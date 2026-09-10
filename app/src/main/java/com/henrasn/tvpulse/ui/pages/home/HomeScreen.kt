package com.henrasn.tvpulse.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.ui.component.GridShimmer
import com.henrasn.tvpulse.ui.component.MovieCard
import com.henrasn.tvpulse.ui.component.PopupNotification
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onMovieSelected: (MovieUiData) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uiState) {
        if (uiState is HomeUiState.Error) {
            val error = (uiState as HomeUiState.Error).errorMessage.asString(context)
            errorMessage = error.ifEmpty { null }
        } else {
            errorMessage = null
        }
    }

    HomeContent(
        uiState = uiState,
        query = viewModel.queryState,
        onMovieSelected = onMovieSelected
    )

    if (errorMessage != null) {
        PopupNotification(
            onRetry = viewModel::retryRequest,
            onDismiss = {
                errorMessage = null
            }
        )
    }
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    query: TextFieldState,
    onMovieSelected: (MovieUiData) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            state = query,
            trailingIcon = {
                if (query.text.isEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search"
                    )
                } else {
                    IconButton(onClick = { query.clearText() }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "remove"
                        )
                    }
                }
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (uiState) {
                is HomeUiState.Error,
                HomeUiState.Idle,
                HomeUiState.Loading -> {
                    items(6) {
                        GridShimmer()
                    }
                }

                is HomeUiState.Success -> {
                    items(
                        items = uiState.movies,
                        key = { it.id },
                        contentType = { "movie" }
                    ) { movie ->
                        val onSelected = remember(movie.id, onMovieSelected) {
                            { onMovieSelected(movie) }
                        }
                        MovieCard(movie = movie, onClick = onSelected)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeContent() {
    TVPulseTheme {
        val uiState = HomeUiState.Idle
        val textFieldState = rememberTextFieldState()
        HomeContent(uiState, textFieldState, onMovieSelected = {})
    }
}

@Preview
@Composable
private fun PreviewHomeContentLoaded() {
    TVPulseTheme {
        val movie = MovieUiData(
            id = 0,
            title = "title",
            image = "image",
            rate = 9.8f,
            genre = "Action"
        )
        val uiState = HomeUiState.Success(listOf(movie))
        val textFieldState = rememberTextFieldState()
        HomeContent(uiState, textFieldState, onMovieSelected = {})
    }
}