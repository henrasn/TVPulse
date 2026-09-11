package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.model.ui.MovieUiData
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesUseCase {
    operator fun invoke(): Flow<List<MovieUiData>>
}