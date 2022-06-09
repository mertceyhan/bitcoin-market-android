package com.mertceyhan.bitcoinmarket.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val teal200 = Color(0xFF03DAC5)
val teal700 = Color(0xFF018786)
val gray700 = Color(0xFF646C81)
val gray900 = Color(0xFF23262D)
val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)

internal val lightColors = lightColors(
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

internal val darkColors = darkColors(
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