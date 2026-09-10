package com.henrasn.tvpulse.data.module

import com.henrasn.tvpulse.data.repository.MovieRepository
import com.henrasn.tvpulse.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindMovieRepo(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}