package com.henrasn.tvpulse.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
    darkColorScheme(
        primary = WarmAmberPrimaryDark,
        onPrimary = DeepCharcoalPrimaryForegroundDark,
        primaryContainer = DarkAmberSubtlePrimaryDark,
        onPrimaryContainer = SoftCreamForegroundDark,
        secondary = MutedTealSecondaryDark,
        onSecondary = CrispWhiteSecondaryForegroundDark,
        background = WarmBlackBackgroundDark,
        onBackground = SoftCreamForegroundDark,
        surface = DarkEspressoSurfaceDark,
        onSurface = SoftCreamForegroundDark,
        surfaceVariant = MutedDarkSurfaceDark,
        onSurfaceVariant = MutedTaupeForegroundDark,
        outline = BorderDark,
        outlineVariant = HoverDarkSurfaceDark,
        error = DestructiveRedDark,
        onError = PureWhitePrimaryForegroundLight
    )

private val LightColorScheme =
    lightColorScheme(
        primary = TerracottaPrimaryLight,
        onPrimary = PureWhitePrimaryForegroundLight,
        primaryContainer = SubtlePeachPrimaryLight,
        onPrimaryContainer = DeepEspressoForegroundLight,
        secondary = SageTealSecondaryLight,
        onSecondary = DeepTealSecondaryForegroundLight,
        background = WarmLinenBackgroundLight,
        onBackground = DeepEspressoForegroundLight,
        surface = PureWhiteSurfaceLight,
        onSurface = DeepEspressoForegroundLight,
        surfaceVariant = MutedSandSurfaceLight,
        onSurfaceVariant = MutedBrownForegroundLight,
        outline = WarmBorderLight,
        outlineVariant = HoverSandSurfaceLight,
        error = DestructiveRedLight,
        onError = PureWhitePrimaryForegroundLight
    )

@Composable
fun TVPulseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
