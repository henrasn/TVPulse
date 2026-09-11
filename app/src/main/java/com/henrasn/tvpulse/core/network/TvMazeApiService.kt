package com.henrasn.tvpulse.core.network

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.dto.movie.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApiService {

    @GET("shows")
    suspend fun getMovies(): List<MovieResponseItem>

    @GET("search/shows")
    suspend fun searchMovie(@Query("q") query: String): List<SearchMovieResponse>
}