package com.henrasn.tvpulse.domain.usecase.search

import com.henrasn.tvpulse.data.model.ui.MovieUiData
import kotlinx.coroutines.flow.Flow

interface MovieSearchUseCase {
    suspend operator fun invoke(query: String): Flow<Result<List<MovieUiData>>>
}