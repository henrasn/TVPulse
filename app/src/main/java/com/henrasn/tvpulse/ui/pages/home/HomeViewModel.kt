package com.henrasn.tvpulse.ui.pages.home

import com.henrasn.tvpulse.domain.usecase.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val movieListUseCase: MovieListUseCase
) {
}