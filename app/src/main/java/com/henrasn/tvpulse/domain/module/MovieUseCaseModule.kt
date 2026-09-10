package com.henrasn.tvpulse.domain.module

import com.henrasn.tvpulse.domain.usecase.MovieListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieUseCaseModule {

    @Binds
    abstract fun bindMoveUseCase(movieListUseCaseImpl: MovieListUseCaseImpl): MovieUseCaseModule
}