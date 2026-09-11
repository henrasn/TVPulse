package com.henrasn.tvpulse.ui.pages.detail

import com.henrasn.tvpulse.core.error.ErrorUiText
import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData

sealed interface DetailMovieUIState {
    data class Success(val movie: DetailMovieUiData) : DetailMovieUIState
    data class Error(val errorMessage: ErrorUiText) : DetailMovieUIState
    object Loading : DetailMovieUIState
    object Idle : DetailMovieUIState
}