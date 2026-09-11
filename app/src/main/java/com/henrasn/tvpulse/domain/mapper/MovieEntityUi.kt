package com.henrasn.tvpulse.domain.mapper

import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.model.ui.MovieUiData

fun MovieEntity.toUiModel() = MovieUiData(
    id = id,
    title = title,
    image = image,
    rate = rate,
    genre = genre
)

fun MovieUiData.toEntity() = MovieEntity(
    id = id,
    title = title,
    image = image,
    rate = rate,
    genre = genre
)