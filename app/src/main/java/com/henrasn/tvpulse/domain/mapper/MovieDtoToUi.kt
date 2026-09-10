package com.henrasn.tvpulse.domain.mapper

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.ui.MovieUiData

fun MovieResponseItem.toUiListModel(): MovieUiData {
    return MovieUiData(
        id = id,
        title = name.orEmpty(),
        image = image?.medium.orEmpty(),
        rate = rating?.average ?: 0f,
        genre = genres?.firstOrNull().orEmpty()
    )
}