package com.henrasn.tvpulse.data.source.remote

import com.henrasn.tvpulse.core.network.NetworkResult
import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.dto.movie.SearchMovieResponse

interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieResponseItem>>

    suspend fun searchMovie(query: String): NetworkResult<List<SearchMovieResponse>>
    suspend fun getDetailMovie(id: Int): NetworkResult<MovieResponseItem>
}