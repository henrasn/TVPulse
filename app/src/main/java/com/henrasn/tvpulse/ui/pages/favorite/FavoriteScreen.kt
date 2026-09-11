package com.henrasn.tvpulse.ui.pages.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.ui.component.FavoriteUiItem
import com.henrasn.tvpulse.ui.component.ListShimmer
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onFavoriteSelected: (Int) -> Unit,
    onExploreMovie: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteContent(
        uiState = uiState,
        onFavoriteSelected = onFavoriteSelected,
        onDeleteFavorite = viewModel::deleteFavorite,
        onExploreMovie = onExploreMovie
    )
}

@Composable
fun FavoriteContent(
    uiState: FavoriteUiState,
    onFavoriteSelected: (Int) -> Unit,
    onDeleteFavorite: (Int) -> Unit,
    onExploreMovie: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            FavoriteUiState.Empty -> {
                FavoriteEmptyState(onExplorMovie = onExploreMovie)
            }

            FavoriteUiState.Loading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(4) {
                        ListShimmer()
                    }
                }
            }

            is FavoriteUiState.Success -> {
                if (uiState.movies.isEmpty()) {
                    FavoriteEmptyState(onExplorMovie = onExploreMovie)
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            Text(
                                text = stringResource(
                                    R.string.label_my_favorite,
                                    uiState.movies.size
                                ), style = MaterialTheme.typography.titleSmall
                            )
                        }
                        items(
                            uiState.movies,
                            key = { movie -> movie.id },
                            contentType = { "favorite" }
                        ) { movie ->
                            FavoriteUiItem(
                                movie = movie,
                                onClick = { onFavoriteSelected(movie.id) },
                                onDelete = { onDeleteFavorite(movie.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFavoriteContent() {
    TVPulseTheme {
        val movieUiData = MovieUiData(
            id = 1,
            title = "title",
            image = "image",
            rate = 9f,
            genre = "Action"
        )
        val uiState = FavoriteUiState.Success(listOf(movieUiData))
        FavoriteContent(
            uiState = uiState,
            onFavoriteSelected = {},
            onDeleteFavorite = {},
            onExploreMovie = {}
        )
    }
}