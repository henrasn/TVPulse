package com.henrasn.tvpulse.core.network

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import retrofit2.http.GET

interface TvMazeApiService {

    @GET("shows")
    suspend fun getMovies(): List<MovieResponseItem>
}