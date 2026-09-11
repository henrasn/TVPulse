package com.henrasn.tvpulse.domain.usecase.detail

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.ui.DetailMovieUiData
import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.domain.mapper.toDetailModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : DetailMovieUseCase {
    override fun invoke(movieId: Int): Flow<Result<DetailMovieUiData>> =
        movieRepository.getDetailMovie(movieId, MovieResponseItem::toDetailModel)
}