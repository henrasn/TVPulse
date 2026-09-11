package com.henrasn.tvpulse.domain.usecase.detail

import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData
import kotlinx.coroutines.flow.Flow

interface DetailMovieUseCase {
    operator fun invoke(movieId: Int): Flow<Result<DetailMovieUiData>>
}