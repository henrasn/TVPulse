package com.henrasn.tvpulse.ui.pages.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrasn.tvpulse.core.error.toUiText
import com.henrasn.tvpulse.domain.usecase.detail.DetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    val detailMovieUseCase: DetailMovieUseCase
) : ViewModel() {
    val movieId = MutableSharedFlow<Int>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val uiState: StateFlow<DetailMovieUIState> = movieId
        .flatMapLatest { movieId ->
            detailMovieUseCase(movieId)
                .map { result ->
                    result.fold(
                        onSuccess = { movie ->
                            DetailMovieUIState.Success(movie)
                        },
                        onFailure = { e ->
                            DetailMovieUIState.Error(e.toUiText())
                        }
                    )
                }

                .onStart {
                    emit(DetailMovieUIState.Loading)
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailMovieUIState.Idle
        )

    fun fetchMovie(movieId: Int) {
        this.movieId.tryEmit(movieId)
    }
}
