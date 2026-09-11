package com.henrasn.tvpulse.domain.usecase.favorite

import com.henrasn.tvpulse.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckFavoriteUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : CheckFavoriteUseCase {
    override fun invoke(movieId: Int): Flow<Boolean> = movieRepository.isFavorite(movieId)
}
