package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.core.di.IoDispatcher
import com.henrasn.tvpulse.core.network.NetworkResult
import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.source.MovieDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieDataSource: MovieDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository {
    override suspend fun <R> getMovies(mapper: (MovieResponseItem) -> R) =
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
}