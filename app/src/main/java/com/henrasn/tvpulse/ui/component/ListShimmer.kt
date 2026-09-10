package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
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
fun ListShimmer() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
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
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(3) {
                ListShimmer()

            }
        }
    }
}