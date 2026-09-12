package com.henrasn.tvpulse.core.network

import com.henrasn.tvpulse.core.error.ApiException
import com.henrasn.tvpulse.core.error.AppException
import com.henrasn.tvpulse.core.error.ErrorUiText
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class NetworkUtilsTest {

    @Test
    fun `safeApiCall returns Success on successful api call`() = runTest {
        val result = safeApiCall { "test data" }

        assertTrue(result is NetworkResult.Success)
        assertEquals("test data", (result as NetworkResult.Success<String>).data)
    }

    @Test
    fun `safeApiCall rethrows CancellationException`() = runTest {
        var caughtException: CancellationException? = null
        try {
            safeApiCall { throw CancellationException("cancelled", null) }
        } catch (e: CancellationException) {
            caughtException = e
        }
        assertTrue(caughtException != null)
        assertEquals("cancelled", caughtException?.message)
    }

    @Test
    fun `safeApiCall returns Failure with DynamicString for ApiException with httpMessage`() =
        runTest {
            val apiException = ApiException(404, 404, "Not Found")
            val result = safeApiCall { throw apiException }

            assertTrue(result is NetworkResult.Failure)
            val failure = result as NetworkResult.Failure
            assertTrue(failure.exception is AppException)
            val appException = failure.exception as AppException
            assertTrue(appException.errorUiText is ErrorUiText.DynamicString)
            val dynamicString = appException.errorUiText as ErrorUiText.DynamicString
            assertEquals("Not Found", dynamicString.value)
            assertEquals(apiException, appException.cause)
        }

    @Test
    fun `safeApiCall returns Failure with DynamicString for ApiException without httpMessage`() =
        runTest {
            val apiException = ApiException(500, 500, null)
            val result = safeApiCall { throw apiException }

            assertTrue(result is NetworkResult.Failure)
            val failure = result as NetworkResult.Failure
            assertTrue(failure.exception is AppException)
            val appException = failure.exception as AppException
            assertTrue(appException.errorUiText is ErrorUiText.DynamicString)
            val dynamicString = appException.errorUiText as ErrorUiText.DynamicString
            assertEquals("500", dynamicString.value)
            assertEquals(apiException, appException.cause)
        }

    @Test
    fun `safeApiCall returns Failure with StringResource for SerializationException`() = runTest {
        val serializationException = SerializationException("parsing error")
        val result = safeApiCall { throw serializationException }

        assertTrue(result is NetworkResult.Failure)
        val failure = result as NetworkResult.Failure
        assertTrue(failure.exception is AppException)
        val appException = failure.exception as AppException
        assertTrue(appException.errorUiText is ErrorUiText.StringResource)
        val stringResource = appException.errorUiText as ErrorUiText.StringResource
        assertEquals(stringResource.resId, com.henrasn.tvpulse.R.string.err_resp_parsing)
        assertEquals(serializationException, appException.cause)
    }

    @Test
    fun `safeApiCall returns Failure with StringResource for IOException`() = runTest {
        val ioException = IOException("network error")
        val result = safeApiCall { throw ioException }

        assertTrue(result is NetworkResult.Failure)
        val failure = result as NetworkResult.Failure
        assertTrue(failure.exception is AppException)
        val appException = failure.exception as AppException
        assertTrue(appException.errorUiText is ErrorUiText.StringResource)
        val stringResource = appException.errorUiText as ErrorUiText.StringResource
        assertEquals(stringResource.resId, com.henrasn.tvpulse.R.string.err_no_connect)
        assertEquals(ioException, appException.cause)
    }

    @Test
    fun `safeApiCall returns Failure with StringResource for generic Exception`() = runTest {
        val genericException = RuntimeException("unknown error")
        val result = safeApiCall { throw genericException }

        assertTrue(result is NetworkResult.Failure)
        val failure = result as NetworkResult.Failure
        assertTrue(failure.exception is AppException)
        val appException = failure.exception as AppException
        assertTrue(appException.errorUiText is ErrorUiText.StringResource)
        val stringResource = appException.errorUiText as ErrorUiText.StringResource
        assertEquals(stringResource.resId, com.henrasn.tvpulse.R.string.err_unknown)
        assertEquals(genericException, appException.cause)
    }
}