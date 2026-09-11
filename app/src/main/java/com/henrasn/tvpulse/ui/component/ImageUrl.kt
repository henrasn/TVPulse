package com.henrasn.tvpulse.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.ui.theme.TVPulseTheme

@Composable
fun ImageUrl(modifier: Modifier = Modifier, url: String, shape: Shape = RectangleShape) {
    Box(contentAlignment = Alignment.Center) {

        var isLoading by remember(url) { mutableStateOf(false) }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = url,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .background(
                    shape = shape,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                .clip(shape),
            onState = { state ->
                isLoading = state is AsyncImagePainter.State.Loading
            }
        )

        if (isLoading || LocalInspectionMode.current) {
            Icon(
                painter = painterResource(R.drawable.img_placeholder),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@SuppressLint("LocalContextResourcesRead")
@OptIn(ExperimentalCoilApi::class)
@PreviewLightDark
@Composable
private fun PreviewImageUrl() {
    TVPulseTheme {
        val context = LocalContext.current
        val previewHandler = AsyncImagePreviewHandler { _ ->
            context.resources
                .getDrawable(R.drawable.sample, null)
                .asImage()
        }

        Box(Modifier.size(64.dp)) {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                ImageUrl(url = "https://static.tvmaze.com/uploads/images/medium_portrait/595/1489169.jpg")
            }
        }
    }
}