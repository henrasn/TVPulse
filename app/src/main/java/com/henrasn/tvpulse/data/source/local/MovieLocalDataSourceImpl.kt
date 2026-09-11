package com.henrasn.tvpulse.data.source.local

import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.source.local.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalDataSource {
    override fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    override suspend fun insertMovie(movie: MovieEntity) = movieDao.insertMovie(movie)

    override suspend fun deleteMovie(id: Int) = movieDao.deleteMovie(id)

    override fun isFavorite(id: Int): Flow<Boolean> = movieDao.isFavorite(id)
}
