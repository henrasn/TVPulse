package com.henrasn.tvpulse.domain.module

import com.henrasn.tvpulse.domain.usecase.detail.DetailMovieUseCase
import com.henrasn.tvpulse.domain.usecase.detail.DetailMovieUseCaseImpl
import com.henrasn.tvpulse.domain.usecase.favorite.AddFavoriteUseCase
import com.henrasn.tvpulse.domain.usecase.favorite.AddFavoriteUseCaseImpl
import com.henrasn.tvpulse.domain.usecase.favorite.CheckFavoriteUseCase
import com.henrasn.tvpulse.domain.usecase.favorite.CheckFavoriteUseCaseImpl
import com.henrasn.tvpulse.domain.usecase.favorite.DeleteFavoriteUseCase
import com.henrasn.tvpulse.domain.usecase.favorite.DeleteFavoriteUseCaseImpl
import com.henrasn.tvpulse.domain.usecase.favorite.FavoriteMoviesUseCase
import com.henrasn.tvpulse.domain.usecase.favorite.FavoriteMoviesUseCaseImpl
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

    @Binds
    abstract fun bindDetailMovieUseCase(detailMovieUseCaseImpl: DetailMovieUseCaseImpl): DetailMovieUseCase

    @Binds
    abstract fun bindFavoritesMoviesUseCase(favoriteMoviesUseCaseImpl: FavoriteMoviesUseCaseImpl): FavoriteMoviesUseCase

    @Binds
    abstract fun bindDeleteFavoriteUseCase(deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl): DeleteFavoriteUseCase

    @Binds
    abstract fun bindInsertAddFavoriteUseCase(addFavoriteUseCaseImpl: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    abstract fun bindCheckFavoriteUseCase(checkFavoriteUseCaseImpl: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase
}