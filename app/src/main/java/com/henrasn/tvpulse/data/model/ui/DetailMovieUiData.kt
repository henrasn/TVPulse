package com.henrasn.tvpulse.data.model.ui

data class DetailMovieUiData(
    val id: Int,
    val title: String,
    val image: String,
    val rate: Float,
    val genre: String,
    val tags: List<String>,
    val synopsis: String
)
