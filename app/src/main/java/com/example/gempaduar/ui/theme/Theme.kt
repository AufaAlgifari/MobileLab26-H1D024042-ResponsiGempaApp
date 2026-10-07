package com.example.gempaduar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = SeismicPrimary,
    onPrimary = SeismicOnPrimary,
    primaryContainer = SeismicPrimaryContainer,
    secondary = SeismicSecondary,
    background = SeismicBackground,
    surface = SeismicBackground,
    onSurface = SeismicOnSurface
)

private val DarkColorScheme = darkColorScheme(
    primary = SeismicPrimaryDark,
    onPrimary = SeismicOnPrimaryDark,
    primaryContainer = SeismicPrimaryContainerDark,
    secondary = SeismicSecondaryDark,
    background = SeismicBackgroundDark,
    surface = SeismicBackgroundDark,
    onSurface = SeismicOnSurfaceDark
)

@Composable
fun GempaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}