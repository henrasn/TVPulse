package com.henrasn.tvpulse.core.app

import android.content.Intent
import android.net.Uri
import androidx.navigation3.runtime.NavKey
import com.henrasn.tvpulse.ui.navigation.DetailMovieNav

fun Intent?.parseDeepLink(): NavKey? {
    val data: Uri = this?.data ?: return null
    val pathSegments = data.pathSegments

    return if (data.scheme == "movieapp" && data.host == "detail" && pathSegments.isNotEmpty()) {
        pathSegments.firstOrNull()?.toIntOrNull()?.let { DetailMovieNav(it) }
    } else {
        null
    }
}