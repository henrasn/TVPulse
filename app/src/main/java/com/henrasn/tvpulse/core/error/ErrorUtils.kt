package com.henrasn.tvpulse.core.error

import com.henrasn.tvpulse.R

fun Throwable.toUiText(): ErrorUiText {
    return (this as? AppException)?.errorUiText
        ?: ErrorUiText.StringResource(R.string.err_unknown)
}