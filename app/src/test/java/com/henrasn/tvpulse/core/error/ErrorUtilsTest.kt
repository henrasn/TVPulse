package com.henrasn.tvpulse.core.error

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ErrorUtilsTest {

    @Test
    fun `toUiText returns AppException errorUiText when throwable is AppException`() {
        val dynamicString = ErrorUiText.DynamicString("custom error")
        val appException = AppException(dynamicString)
        val result = appException.toUiText()

        assertEquals(dynamicString, result)
    }

    @Test
    fun `toUiText returns StringResource err_unknown for non-AppException`() {
        val genericException = RuntimeException("unknown")
        val result = genericException.toUiText()

        assertTrue(result is ErrorUiText.StringResource)
        val stringResource = result as ErrorUiText.StringResource
        assertEquals(com.henrasn.tvpulse.R.string.err_unknown, stringResource.resId)
    }

    @Test
    fun `toUiText returns StringResource err_unknown for null throwable cause`() {
        val exception = Exception()
        val result = exception.toUiText()

        assertTrue(result is ErrorUiText.StringResource)
        val stringResource = result as ErrorUiText.StringResource
        assertEquals(com.henrasn.tvpulse.R.string.err_unknown, stringResource.resId)
    }
}