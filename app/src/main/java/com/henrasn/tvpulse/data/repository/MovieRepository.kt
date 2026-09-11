package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun <R> getMovies(mapper: (MovieResponseItem) -> R): Flow<Result<List<R>>>
    fun <R> searchMovies(
        query: String,
        mapper: (MovieResponseItem) -> R
    ): Flow<Result<List<R>>>

    fun <R> getDetailMovie(movieId: Int, mapper: (MovieResponseItem) -> R): Flow<Result<R>>
}