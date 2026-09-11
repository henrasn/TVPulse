package com.henrasn.tvpulse.ui.pages.home

import com.henrasn.tvpulse.core.error.ErrorUiText
import com.henrasn.tvpulse.data.model.ui.MovieUiData

sealed interface HomeUiState {
    data class Success(val movies: List<MovieUiData>) : HomeUiState
    data class Error(val errorMessage: ErrorUiText) : HomeUiState
    object Loading : HomeUiState
    object Idle : HomeUiState
}