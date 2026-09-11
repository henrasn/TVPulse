package com.henrasn.tvpulse.data.source

import com.henrasn.tvpulse.core.network.NetworkResult
import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem

interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieResponseItem>>
}