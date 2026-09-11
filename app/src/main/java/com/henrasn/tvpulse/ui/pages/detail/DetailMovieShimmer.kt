package com.henrasn.tvpulse.ui.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.ui.theme.TVPulseTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun DetailMovieShimmer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
private fun PreviewDetailMovieContentEmpty() {
    TVPulseTheme {
        val uiState = DetailMovieUIState.Idle
        DetailMovieShimmer()
    }
}