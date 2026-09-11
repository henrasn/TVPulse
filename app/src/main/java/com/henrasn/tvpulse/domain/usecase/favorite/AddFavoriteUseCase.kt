package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData

interface AddFavoriteUseCase {
    suspend operator fun invoke(movie: DetailMovieUiData)
}