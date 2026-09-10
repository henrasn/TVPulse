package com.henrasn.tvpulse.domain.usecase.search

import com.henrasn.tvpulse.data.model.ui.MovieUiData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieSearchUseCaseImpl @Inject constructor(

) : MovieSearchUseCase {
    // TODO: implement action use case
    override suspend fun invoke(query: String) =
        flow { emit(Result.success<List<MovieUiData>>(listOf())) }
}