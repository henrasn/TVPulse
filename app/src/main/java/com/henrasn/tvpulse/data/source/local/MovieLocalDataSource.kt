package com.henrasn.tvpulse.data.source.local

import com.henrasn.tvpulse.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getAllMovies(): Flow<List<MovieEntity>>
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovie(id: Int)
    fun isFavorite(id: Int): Flow<Boolean>
}
