package com.henrasn.tvpulse.core.error

class AppException(val errorUiText: ErrorUiText, cause: Throwable? = null) : Exception(cause)