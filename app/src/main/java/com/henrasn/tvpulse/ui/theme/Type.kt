package com.henrasn.tvpulse.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography =
    Typography(
        displayLarge = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            letterSpacing = (-0.5).sp
        ),
        displayMedium = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.25).sp
        ),
        displaySmall = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.sp
        ),

        // Screen Titles & Section Headlines (e.g., "Stranger Things", "Favorit Saya", Dialog Title)
        headlineLarge = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),

        // Card Titles, Modal Headings ("Sinopsis", "Breaking Bad", "Pencarian Tidak Ditemukan")
        titleLarge = TextStyle(
            fontFamily = EpilogueFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),

        // Synopsis, Dialog Descriptions, Empty States
        bodyLarge = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.25.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),

        // Section Labels, Navigation Tabs, CTA Buttons ("HOME", "FAVORITE", "TAMBAH KE FAVORIT")
        labelLarge = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 1.25.sp // Tracking for uppercase buttons/tabs
        ),
        // Section subheaders: "DAFTAR ACARA POPULER (30 Film)"
        labelMedium = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.0.sp
        ),
        // Metadata tags & badges: "Drama", "Horror", "60 min", "Berjalan", Rating "8.7"
        labelSmall = TextStyle(
            fontFamily = ManropeFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp
        )
    )

