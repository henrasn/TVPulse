package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.repository.MovieRepository
import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : DeleteFavoriteUseCase {
    override suspend fun invoke(movieId: Int) = movieRepository.deleteMovie(movieId)
}