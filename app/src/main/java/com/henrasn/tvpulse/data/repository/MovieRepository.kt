package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun <R> getMovies(mapper: (MovieResponseItem) -> R): Flow<Result<List<R>>>
    fun <R> searchMovies(
        query: String,
        mapper: (MovieResponseItem) -> R
    ): Flow<Result<List<R>>>

    fun <R> getDetailMovie(movieId: Int, mapper: (MovieResponseItem) -> R): Flow<Result<R>>
    fun <R> getFavoriteMovie(mapper: (MovieEntity) -> R): Flow<List<R>>
    suspend fun deleteMovie(movieId: Int)
}