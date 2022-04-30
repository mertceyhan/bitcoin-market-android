package com.mertceyhan.bitcoinmarket.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BitcoinMarketTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = getColors(isSystemInDarkTheme),
        typography = typography,
        content = content
    )
}

internal fun getColors(isSystemInDarkTheme: Boolean): Colors {
    return if (isSystemInDarkTheme) darkColors else lightColors
}