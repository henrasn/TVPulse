package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.ui.theme.TVPulseTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun GridShimmer(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2 / 3f, true)
            .shimmer(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(Modifier
            .fillMaxSize()
            .background(Color.Gray))
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFAF7F2)
@Composable
private fun PreviewGridShimmer() {
    TVPulseTheme {
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(3) {
                GridShimmer()

            }
        }
    }
}