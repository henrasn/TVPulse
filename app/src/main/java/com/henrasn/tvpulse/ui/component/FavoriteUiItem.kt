package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.data.model.ui.MovieUiData
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun FavoriteUiItem(
    modifier: Modifier = Modifier,
    movie: MovieUiData,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageUrl(
                modifier = Modifier.size(64.dp),
                url = movie.image,
                shape = MaterialTheme.shapes.medium
            )
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(text = movie.genre, style = MaterialTheme.typography.titleMedium)
            }

            Text(
                text = stringResource(R.string.action_delete),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.error)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewFavoriteUiItem() {
    TVPulseTheme {
        val movieUiData = MovieUiData(
            id = 0,
            title = "Stanger Things",
            image = "https://static.tvmaze.com/uploads/images/medium_portrait/595/1489169.jpg",
            rate = 8.4f,
            genre = "Action"
        )
        LazyColumn {
            items(4) {
                FavoriteUiItem(movie = movieUiData) {

                }
            }
        }
    }
}