package com.henrasn.tvpulse.ui.pages.favorite

import com.henrasn.tvpulse.data.model.ui.MovieUiData

sealed interface FavoriteUiState {
    data class Success(val movies: List<MovieUiData>) : FavoriteUiState
    object Loading : FavoriteUiState
    object Empty : FavoriteUiState
}