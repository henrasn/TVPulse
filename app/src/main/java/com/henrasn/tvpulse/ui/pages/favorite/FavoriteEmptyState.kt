package com.henrasn.tvpulse.ui.pages.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun FavoriteEmptyState(onExplorMovie: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(R.drawable.img_favorite_empty),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.title_empty_favorite),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(R.string.msg_empty_favorite),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            OutlinedButton(shape = MaterialTheme.shapes.medium, onClick = onExplorMovie) {
                Text(stringResource(R.string.action_explore_movie))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavoriteEmptyState() {
    TVPulseTheme {
        FavoriteEmptyState() {}
    }
}