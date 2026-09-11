package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData
import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.domain.mapper.toEntity
import javax.inject.Inject

class AddFavoriteUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : AddFavoriteUseCase {
    override suspend fun invoke(movie: DetailMovieUiData) =
        movieRepository.addFavorite(movie.toEntity())
}