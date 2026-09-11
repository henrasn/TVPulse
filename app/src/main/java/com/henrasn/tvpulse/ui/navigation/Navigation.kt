package com.henrasn.tvpulse.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.henrasn.tvpulse.ui.pages.detail.DetailMovieScreen
import com.henrasn.tvpulse.ui.pages.main.MainScreen

@Composable
fun MainNavigation() {
    val backStack = rememberNavBackStack(Main)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<Main> {
                    MainScreen(
                        onMovieSelected = { movieId ->
                            backStack.add(DetailMovieNav(movieId))
                        }
                    )
                }

                entry<DetailMovieNav> { param ->
                    DetailMovieScreen(param.movieId)
                }
            },
    )
}
