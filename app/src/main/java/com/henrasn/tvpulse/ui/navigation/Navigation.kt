package com.henrasn.tvpulse.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.henrasn.tvpulse.ui.pages.detail.DetailMovieScreen
import com.henrasn.tvpulse.ui.pages.main.MainScreen

@Composable
fun MainNavigation(
    initialKeys: List<NavKey>,
    newKey: NavKey? = null,
    onNewKeyHandled: () -> Unit = {}
) {
    val backStack = rememberNavBackStack(*initialKeys.toTypedArray())

    LaunchedEffect(newKey) {
        if (newKey != null) {
            backStack.add(newKey)
            onNewKeyHandled()
        }
    }

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
                    DetailMovieScreen(param.movieId) {
                        backStack.removeLastOrNull()
                    }
                }
            },
    )
}
