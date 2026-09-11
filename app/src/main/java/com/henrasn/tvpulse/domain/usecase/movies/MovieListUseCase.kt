package com.henrasn.tvpulse.domain.usecase.movies

import com.henrasn.tvpulse.data.model.ui.MovieUiData
import kotlinx.coroutines.flow.Flow

interface MovieListUseCase {
    suspend operator fun invoke(): Flow<Result<List<MovieUiData>>>
}