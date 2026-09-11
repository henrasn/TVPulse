package com.henrasn.tvpulse.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

interface CheckFavoriteUseCase {
    operator fun invoke(movieId: Int): Flow<Boolean>
}
