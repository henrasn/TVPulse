package com.henrasn.tvpulse.domain.usecase.search

import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.domain.mapper.toUiListModel
import javax.inject.Inject

class MovieSearchUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
) : MovieSearchUseCase {

    override suspend fun invoke(query: String) =
        movieRepository.searchMovies(query, MovieResponseItem::toUiListModel)
}