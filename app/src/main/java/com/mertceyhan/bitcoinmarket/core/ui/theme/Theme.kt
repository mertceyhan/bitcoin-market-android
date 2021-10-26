package com.mertceyhan.bitcoinmarket.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val lightThemeColors = lightColors(
    primary = black,
    primaryVariant = black,
    onPrimary = white,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = black,
    surface = white,
    onSurface = black,
    background = white,
    onBackground = black,
)

private val darkThemeColors = darkColors(
    primary = black,
    primaryVariant = black,
    onPrimary = white,
    secondary = teal200,
    secondaryVariant = teal200,
    onSecondary = black,
    surface = black,
    onSurface = white,
    background = black,
    onBackground = white,
)

@Composable
fun BitcoinMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) darkThemeColors else lightThemeColors,
        content = content
    )
}