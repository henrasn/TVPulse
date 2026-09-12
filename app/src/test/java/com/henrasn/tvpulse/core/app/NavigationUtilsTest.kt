package com.henrasn.tvpulse.core.app

import android.content.Intent
import android.net.Uri
import androidx.navigation3.runtime.NavKey
import com.henrasn.tvpulse.ui.navigation.DetailMovieNav
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NavigationUtilsTest {

    @Test
    fun `parseDeepLink returns DetailMovieNav for valid movieapp scheme`() {
        val intent = Intent().apply {
            data = Uri.parse("movieapp://detail/123")
        }

        val result = intent.parseDeepLink()

        assertTrue(result is DetailMovieNav)
        assertEquals(123, (result as DetailMovieNav).movieId)
    }

    @Test
    fun `parseDeepLink returns null for null intent`() {
        val result: NavKey? = (null as Intent?).parseDeepLink()

        assertNull(result)
    }

    @Test
    fun `parseDeepLink returns null for intent with null data`() {
        val intent = Intent()

        val result = intent.parseDeepLink()

        assertNull(result)
    }

    @Test
    fun `parseDeepLink returns null for wrong scheme`() {
        val intent = Intent().apply {
            data = Uri.parse("http://detail/123")
        }

        val result = intent.parseDeepLink()

        assertNull(result)
    }

    @Test
    fun `parseDeepLink returns null for wrong host`() {
        val intent = Intent().apply {
            data = Uri.parse("movieapp://other/123")
        }

        val result = intent.parseDeepLink()

        assertNull(result)
    }

    @Test
    fun `parseDeepLink returns null for empty path segments`() {
        val intent = Intent().apply {
            data = Uri.parse("movieapp://detail")
        }

        val result = intent.parseDeepLink()

        assertNull(result)
    }

    @Test
    fun `parseDeepLink returns null for non-integer movieId`() {
        val intent = Intent().apply {
            data = Uri.parse("movieapp://detail/abc")
        }

        val result = intent.parseDeepLink()

        assertNull(result)
    }
}