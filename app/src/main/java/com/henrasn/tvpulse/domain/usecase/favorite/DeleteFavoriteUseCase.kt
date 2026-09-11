package com.henrasn.tvpulse.domain.usecase.favorite

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(movieId: Int)
}