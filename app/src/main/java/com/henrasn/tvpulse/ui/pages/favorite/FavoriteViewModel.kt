package com.henrasn.tvpulse.ui.pages.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrasn.tvpulse.domain.usecase.favorite.DeleteFavoriteUseCase
import com.henrasn.tvpulse.domain.usecase.favorite.FavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val favoriteMoviesUseCase: FavoriteMoviesUseCase,
    val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    val uiState: StateFlow<FavoriteUiState> = favoriteMoviesUseCase()
        .map { movies ->
            if (movies.isEmpty()) FavoriteUiState.Empty
            else FavoriteUiState.Success(movies)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoriteUiState.Loading
        )

    fun deleteFavorite(movieId: Int) {
        viewModelScope.launch {
            deleteFavoriteUseCase(movieId)
        }
    }
}