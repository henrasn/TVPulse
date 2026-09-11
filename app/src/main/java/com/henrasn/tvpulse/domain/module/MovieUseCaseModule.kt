package com.henrasn.tvpulse.domain.module

import com.henrasn.tvpulse.domain.usecase.movies.MovieListUseCase
import com.henrasn.tvpulse.domain.usecase.movies.MovieListUseCaseImpl
import com.henrasn.tvpulse.domain.usecase.search.MovieSearchUseCase
import com.henrasn.tvpulse.domain.usecase.search.MovieSearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieUseCaseModule {

    @Binds
    abstract fun bindMoveUseCase(movieListUseCaseImpl: MovieListUseCaseImpl): MovieListUseCase

    @Binds
    abstract fun bindSearchUseCase(searchUseCaseImpl: MovieSearchUseCaseImpl): MovieSearchUseCase
}