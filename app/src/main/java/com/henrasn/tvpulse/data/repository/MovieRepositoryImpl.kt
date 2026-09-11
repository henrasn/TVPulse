package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.core.di.IoDispatcher
import com.henrasn.tvpulse.core.network.NetworkResult
import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.source.local.MovieLocalDataSource
import com.henrasn.tvpulse.data.source.remote.MovieDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieDataSource: MovieDataSource,
    val localDataSource: MovieLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {
    override fun <R> getMovies(mapper: (MovieResponseItem) -> R) =
        flow {
            emit(movieDataSource.getMovies())
        }.map { result ->
            when (result) {
                is NetworkResult.Failure -> Result.failure(result.exception)
                is NetworkResult.Success -> {
                    val respMovieList = result.data
                    val mappedMovieList = respMovieList
                        .take(30)
                        .map(mapper)
                    Result.success(mappedMovieList)
                }
            }
        }.flowOn(dispatcher)

    override fun <R> searchMovies(query: String, mapper: (MovieResponseItem) -> R) =
        flow {
            emit(movieDataSource.searchMovie(query))
        }.map { result ->
            when (result) {
                is NetworkResult.Failure -> Result.failure(result.exception)
                is NetworkResult.Success -> {
                    val respMovieList = result.data
                    val mappedMovieList = respMovieList
                        .mapNotNull { response -> response.show }
                        .map(mapper)
                    Result.success(mappedMovieList)
                }
            }
        }.flowOn(dispatcher)

    override fun <R> getDetailMovie(movieId: Int, mapper: (MovieResponseItem) -> R) =
        flow {
            emit(movieDataSource.getDetailMovie(movieId))
        }.map { result ->
            when (result) {
                is NetworkResult.Failure -> Result.failure(result.exception)
                is NetworkResult.Success -> {
                    val mappedMovieList = mapper(result.data)
                    Result.success(mappedMovieList)
                }
            }
        }.flowOn(dispatcher)

    override fun <R> getFavoriteMovie(mapper: (MovieEntity) -> R): Flow<List<R>> {
        return localDataSource.getAllMovies()
            .map { movies ->
                movies.map { mapper(it) }
            }.flowOn(dispatcher)
    }

    override fun isFavorite(movieId: Int): Flow<Boolean> =
        localDataSource.isFavorite(movieId).flowOn(dispatcher)

    override suspend fun deleteMovie(movieId: Int) = localDataSource.deleteMovie(movieId)
    override suspend fun addFavorite(movie: MovieEntity) = localDataSource.insertMovie(movie)
}