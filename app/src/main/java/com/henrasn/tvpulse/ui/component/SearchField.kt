package com.henrasn.tvpulse.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun SearchField(query: TextFieldState) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        state = query,
        trailingIcon = {
            if (query.text.isEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search"
                )
            } else {
                IconButton(onClick = { query.clearText() }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "remove"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewSearchFieldField() {
    TVPulseTheme() {
        val fieldState = rememberTextFieldState()
        SearchField(fieldState)
    }
}