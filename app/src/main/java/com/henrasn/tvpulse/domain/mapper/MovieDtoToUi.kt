package com.henrasn.tvpulse.domain.mapper

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData
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

fun MovieResponseItem.toDetailModel(): DetailMovieUiData {
    val tags = mutableListOf<String>()
    genres?.filterNotNull()?.let { genreList ->
        tags.addAll(genreList)
    }
    runtime?.let { time ->
        tags.add("$time min")
    }
    status?.let {
        tags.add(status)
    }

    return DetailMovieUiData(
        id = id,
        title = name.orEmpty(),
        image = image?.medium.orEmpty(),
        tags = tags,
        synopsis = summary.orEmpty()
    )
}