package com.henrasn.tvpulse.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
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
import com.henrasn.tvpulse.ui.component.SearchField
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun MovieEmptyState(query: TextFieldState) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(R.drawable.ic_telescope),
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.title_not_found),
                style = MaterialTheme.typography.headlineMedium
            )

            SearchField(query, false)

            Text(
                text = stringResource(R.string.msg_movie_not_found),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieEmptyState() {
    TVPulseTheme() {
        val textFieldState = rememberTextFieldState()
        MovieEmptyState(textFieldState)
    }
}