package com.henrasn.tvpulse.core.network

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Failure(val exception: Throwable) : NetworkResult<Nothing>
}