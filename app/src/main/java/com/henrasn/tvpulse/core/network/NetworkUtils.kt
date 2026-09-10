package com.henrasn.tvpulse.core.network

import com.henrasn.tvpulse.R
import com.henrasn.tvpulse.core.error.ApiException
import com.henrasn.tvpulse.core.error.AppException
import com.henrasn.tvpulse.core.error.ErrorUiText
import kotlinx.serialization.SerializationException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): NetworkResult<T> {
    return try {
        NetworkResult.Success(apiCall())
    } catch (e: CancellationException) {
        throw e
    } catch (e: ApiException) {
        NetworkResult.Failure(
            AppException(
                ErrorUiText.DynamicString(
                    e.httpMessage ?: e.httpCode.toString()
                ), e
            )
        )
    } catch (e: SerializationException) {
        NetworkResult.Failure(
            AppException(
                ErrorUiText.StringResource(R.string.err_resp_parsing),
                e
            )
        )
    } catch (e: IOException) {
        NetworkResult.Failure(AppException(ErrorUiText.StringResource(R.string.err_no_connect), e))
    } catch (e: Exception) {
        NetworkResult.Failure(AppException(ErrorUiText.StringResource(R.string.err_unknown), e))
    }
}