package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun <R> getMovies(mapper: (MovieResponseItem) -> R): Flow<Result<List<R>>>
}