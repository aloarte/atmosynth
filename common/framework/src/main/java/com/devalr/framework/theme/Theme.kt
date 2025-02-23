package com.devalr.framework.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        primary = Orange400,
        secondary = Gray800,
        tertiary = Orange300,
        background = Gray50,
        surface = Gray200,
        onPrimary = Gray50,
        onSecondary = Gray50,
        onTertiary = Gray50,
        onBackground = Gray900,
        onSurface = Gray900
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = Orange900,
        secondary = Gray800,
        tertiary = Orange900,
        background = Gray500,
        surface = Gray600,
        onPrimary = Gray200,
        onSecondary = Gray200,
        onTertiary = Gray200,
        onBackground = Gray200,
        onSurface = Gray200
    )

@Composable
fun AtmosynthTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
