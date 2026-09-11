package com.henrasn.tvpulse.data.source

import com.henrasn.tvpulse.core.network.TvMazeApiService
import com.henrasn.tvpulse.core.network.safeApiCall
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    val tvMazeApiService: TvMazeApiService
) : MovieDataSource {
    override suspend fun getMovies() = safeApiCall { tvMazeApiService.getMovies() }
    override suspend fun searchMovie(query: String) =
        safeApiCall { tvMazeApiService.searchMovie(query) }

}