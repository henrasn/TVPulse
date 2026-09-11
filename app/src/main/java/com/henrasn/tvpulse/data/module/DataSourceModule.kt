package com.henrasn.tvpulse.data.module

import com.henrasn.tvpulse.data.source.local.MovieLocalDataSource
import com.henrasn.tvpulse.data.source.local.MovieLocalDataSourceImpl
import com.henrasn.tvpulse.data.source.remote.MovieDataSource
import com.henrasn.tvpulse.data.source.remote.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource

    @Binds
    abstract fun bindMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource
}
