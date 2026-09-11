package com.henrasn.tvpulse.ui.pages.home

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrasn.tvpulse.core.error.ErrorUiText
import com.henrasn.tvpulse.core.error.toUiText
import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.domain.usecase.movies.MovieListUseCase
import com.henrasn.tvpulse.domain.usecase.search.MovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    val movieListUseCase: MovieListUseCase,
    val searchUseCase: MovieSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState = _uiState.asStateFlow()
    val queryState = TextFieldState()
    val queryTrigger = snapshotFlow { queryState.text }
        .debounce(300.milliseconds)
        .map(CharSequence::trim)
        .distinctUntilChanged()
    val retryTrigger = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    init {
        observerQuery()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun observerQuery() {
        merge(queryTrigger, retryTrigger.map { queryState.text })
            .flatMapLatest { query ->
                if (query.length > 2) {
                    searchUseCase(query.toString())
                } else if (query.isEmpty()) {
                    movieListUseCase()
                } else {
                    emptyFlow()
                }
            }
            .onEach(::collectMoviesResult)
            .launchIn(viewModelScope)

    }

    fun loadMovies() {
        viewModelScope.launch {
            _uiState.update { HomeUiState.Loading }

            movieListUseCase()
                .collect { result ->
                    collectMoviesResult(result)
                }
        }
    }

    suspend fun collectMoviesResult(result: Result<List<MovieUiData>>) {
        result.onSuccess { movies ->
            _uiState.emit(HomeUiState.Success(movies))
        }.onFailure { e ->
            _uiState.emit(HomeUiState.Error(e.toUiText()))
        }
    }

    fun retryRequest() {
        _uiState.update { HomeUiState.Error(ErrorUiText.DynamicString("")) }
        retryTrigger.tryEmit(Unit)
    }
}