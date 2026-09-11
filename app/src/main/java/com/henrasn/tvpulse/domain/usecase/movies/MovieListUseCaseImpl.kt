package com.henrasn.tvpulse.domain.usecase.movies

import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.domain.mapper.toUiListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : MovieListUseCase {
    override suspend fun invoke(): Flow<Result<List<MovieUiData>>> =
        movieRepository.getMovies { response -> response.toUiListModel() }
}