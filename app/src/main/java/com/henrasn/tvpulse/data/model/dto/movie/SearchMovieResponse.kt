package com.henrasn.tvpulse.data.model.dto.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovieResponse(

    @SerialName("score")
    val score: Float? = null,

    @SerialName("show")
    val show: MovieResponseItem? = null
)
