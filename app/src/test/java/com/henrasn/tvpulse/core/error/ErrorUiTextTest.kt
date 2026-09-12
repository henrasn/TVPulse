package com.henrasn.tvpulse.core.error

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ErrorUiTextTest {

    @Test
    fun `DynamicString asString returns value`() {
        val dynamicString = ErrorUiText.DynamicString("test value")
        val context = ApplicationProvider.getApplicationContext<Context>()

        val result = dynamicString.asString(context)

        assertEquals("test value", result)
    }

    @Test
    fun `StringResource asString returns string from context`() {
        val stringResource = ErrorUiText.StringResource(com.henrasn.tvpulse.R.string.err_unknown)
        val context = ApplicationProvider.getApplicationContext<Context>()

        val result = stringResource.asString(context)

        assertEquals("Unknown Error", result)
    }
}