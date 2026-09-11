package com.henrasn.tvpulse.ui.pages.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.henrasn.tvpulse.core.app.parseDeepLink
import com.henrasn.tvpulse.ui.navigation.Main
import com.henrasn.tvpulse.ui.navigation.MainNavigation
import com.henrasn.tvpulse.ui.theme.TVPulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var deepLinkKey by mutableStateOf<NavKey?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLink = intent.parseDeepLink()
        val initialKeys = if (deepLink != null) listOf(Main, deepLink) else listOf(Main)

        enableEdgeToEdge()
        setContent {
            TVPulseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(
                        initialKeys = initialKeys,
                        newKey = deepLinkKey,
                        onNewKeyHandled = { deepLinkKey = null }
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        intent.parseDeepLink()?.let { navKey ->
            deepLinkKey = navKey
        }
    }
}
