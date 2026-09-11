package com.henrasn.tvpulse.ui.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.henrasn.tvpulse.ui.component.ChipGroup
import com.henrasn.tvpulse.ui.component.ImageUrl
import com.henrasn.tvpulse.ui.component.PopupNotification
import com.henrasn.tvpulse.ui.theme.TVPulseTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun DetailMovieScreen(
    movieId: Int,
    viewModel: DetailMovieViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uiState) {
        if (uiState is DetailMovieUIState.Error) {
            val error = (uiState as DetailMovieUIState.Error).errorMessage.asString(context)
            errorMessage = error.ifEmpty { null }
        } else {
            errorMessage = null
        }
    }


    DetailMovieContent(uiState = uiState, onBack = onBack, addRemoveFavorite = {})

    if (errorMessage != null) {
        PopupNotification(onDismiss = {
            errorMessage = null
        }, onRetry = {
            viewModel.fetchMovie(movieId)
        })
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMovie(movieId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMovieContent(
    modifier: Modifier = Modifier,
    uiState: DetailMovieUIState,
    addRemoveFavorite: () -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text((uiState as? DetailMovieUIState.Success)?.movie?.title.orEmpty())
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            if (uiState is DetailMovieUIState.Success) {
                val movie = uiState.movie
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(Modifier.size(8.dp))
                    ImageUrl(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3 / 2f),
                        url = movie.image,
                        shape = MaterialTheme.shapes.medium
                    )
                    ChipGroup(Modifier.fillMaxWidth(), movie.tags)

                    Button(
                        modifier = modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        onClick = addRemoveFavorite,
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(
                                imageVector = Icons.Outlined.BookmarkBorder,
                                contentDescription = null
                            )
                            Text("Add To Favorite")
                        }
                    }

                    Spacer(Modifier.size(8.dp))
                    Text(text = "Sinopsis", style = MaterialTheme.typography.headlineMedium)
                    Text(
                        text = AnnotatedString.fromHtml(movie.synopsis),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                DetailMovieShimmer()
            }
        }
    }
}

@Composable
fun DetailMovieShimmer() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Surface(
            modifier = Modifier
                .aspectRatio(3 / 2f)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                .shimmer()

        ) {}

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            repeat(3) {
                Surface(
                    Modifier
                        .size(height = 34.dp, width = 100.dp)
                        .background(
                            Color.Gray,
                            MaterialTheme.shapes.extraLarge
                        )
                        .shimmer()
                ) {}
            }
        }

        Surface(
            Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                .shimmer()
        ) {}

        Surface(
            Modifier
                .size(height = 44.dp, width = 230.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                .shimmer()
        ) {}
        Surface(
            Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                .shimmer()
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailMovieContent() {
    TVPulseTheme {
        val uiState = DetailMovieUIState.Idle
        DetailMovieContent(uiState = uiState, onBack = {}, addRemoveFavorite = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailMovieContentEmpty() {
    TVPulseTheme {
        val uiState = DetailMovieUIState.Idle
        DetailMovieShimmer()
    }
}