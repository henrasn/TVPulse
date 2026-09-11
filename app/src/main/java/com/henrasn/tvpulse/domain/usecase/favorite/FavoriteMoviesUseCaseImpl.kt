package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.domain.mapper.toUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMoviesUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : FavoriteMoviesUseCase {
    override fun invoke(): Flow<List<MovieUiData>> =
        movieRepository.getFavoriteMovie(MovieEntity::toUiModel)

}