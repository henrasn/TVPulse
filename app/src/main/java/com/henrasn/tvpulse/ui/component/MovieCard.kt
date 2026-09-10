package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: MovieUiData,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            ImageUrl(modifier = Modifier.aspectRatio(2 / 3f), url = movie.image)

            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        imageVector = Icons.Filled.Star,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                    Text(
                        text = movie.rate.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@PreviewLightDark()
@Composable
private fun PreviewMovieCard() {
    TVPulseTheme {
        val movieUiData = MovieUiData(
            id = 0,
            title = "Stanger Things",
            image = "https://static.tvmaze.com/uploads/images/medium_portrait/595/1489169.jpg",
            rate = 8.4f,
            genre = "Action"
        )

        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(4) {
                MovieCard(movie = movieUiData) {

                }
            }
        }
    }
}

