package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    items: List<String>
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between chips horizontally
        verticalArrangement = Arrangement.spacedBy(4.dp)     // Space between rows vertically
    ) {
        items.forEach { items ->
            SimpleChip(items)
        }
    }
}

@Preview
@Composable
private fun PreviewChipGroup() {
    TVPulseTheme() {
        ChipGroup(Modifier.width(200.dp), listOf("Action", "Sci-Fi", "Horror", "Comedy", "Crime"))
    }
}